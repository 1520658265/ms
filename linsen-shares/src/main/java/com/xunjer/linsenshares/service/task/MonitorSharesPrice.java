package com.xunjer.linsenshares.service.task;

import cn.hutool.core.date.DateUtil;
import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.util.AliDateUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title: 监控价格并提醒
 * @Description:
 * @date 2020/8/1714:12
 */
@Slf4j
public class MonitorSharesPrice implements Runnable {

    private long finishTime;

    private boolean flag = true;

    private RestTemplate restTemplate;

    private EmailService emailService;

    private String sharesCode;

    private float minPrice;

    public MonitorSharesPrice(String code, float minPrice, RestTemplate restTemplate, EmailService emailService) throws ParseException {
        this.sharesCode = code;
        this.minPrice = minPrice;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
        finishTime = AliDateUtils.getTimestamp("15:30:00");
    }

    private void checkTime(){
        flag =  System.currentTimeMillis()>finishTime;
    }

    @SneakyThrows
    @Override
    public void run() {
        checkTime();
        log.info("开始监控："+sharesCode);
        while (flag){
            String result = restTemplate.getForObject("http://hq.sinajs.cn/list="+sharesCode,String.class, Collections.EMPTY_MAP);
            String content = result.substring(result.indexOf("\""), result.lastIndexOf("\""));
            String[] array = content.split(",",-1);
            log.info("当前数据："+ DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"---"+array[3]);
            Thread.sleep(10000L);
            if(Float.parseFloat(array[3])<minPrice){
                log.info("监控结束");
                emailService.sendSimpleMail("1520658265@qq.com","监控坏结果","当前价格"+array[3]);
                flag = false;
            }
        }
        log.info("监控结束");
    }
}
