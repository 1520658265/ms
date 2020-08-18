package com.xunjer.linsenshares.service.task;

import cn.hutool.core.date.DateUtil;
import com.xunjer.linsencommon.dictionary.CommonKey;
import com.xunjer.linsencommon.utils.StringSplitUtils;
import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.entity.SharesMonitor;
import com.xunjer.linsenshares.service.ISharesMonitorService;
import com.xunjer.linsenshares.util.AliDateUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
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

    private String[] data;

    private RestTemplate restTemplate;

    private EmailService emailService;

    private SharesMonitor sharesMonitor;

    private ISharesMonitorService iSharesMonitorService;

    public MonitorSharesPrice(SharesMonitor sharesMonitor, RestTemplate restTemplate, EmailService emailService, ISharesMonitorService iSharesMonitorService) throws ParseException {
        this.sharesMonitor = sharesMonitor;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
        this.iSharesMonitorService = iSharesMonitorService;
        finishTime = AliDateUtils.getTimestamp(CommonKey.endTime);
    }

    private void checkTime(){
        flag =  System.currentTimeMillis()<finishTime;
        if(!flag){
            String endString = sharesMonitor.getSharesCode()+"任务已结束，结果正常结束；结束价格:"+data[3]+"其实价格"+data[1];
            emailService.sendSimpleMail("1520658265@qq.com","监控结果",endString);
            sharesMonitor.setSharesName(data[0]);
            sharesMonitor.setResult(endString);
            iSharesMonitorService.updateSharesMonitor(sharesMonitor);
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("开始监控："+sharesMonitor.getSharesCode());
        while (flag){
            checkTime();
            String result = restTemplate.getForObject("http://hq.sinajs.cn/list="+sharesMonitor.getSharesCode(),String.class, Collections.EMPTY_MAP);
            String content = result.substring(result.indexOf("\""), result.lastIndexOf("\""));
            String[] array = StringSplitUtils.splitByComma(content);
            data = array;
            Thread.sleep(sharesMonitor.getMonitorTime()*1000L);
            boolean fail = BigDecimal.valueOf(Double.parseDouble(array[3])).compareTo(sharesMonitor.getMonitorPrice()) < 0;
            if(fail){
                String endString = sharesMonitor.getSharesCode()+"任务已结束，结果失败，已达到阈值，请及时处理，当前时间"+DateUtil.format(new Date(),"yyyy-MM:dd HH:mm:ss");
                log.info(sharesMonitor.getSharesCode()+"-监控结束");
                emailService.sendSimpleMail("1520658265@qq.com","监控结果",endString);
                flag = false;
                sharesMonitor.setSharesName(data[0]);
                sharesMonitor.setResult(endString);
                iSharesMonitorService.updateSharesMonitor(sharesMonitor);
            }
        }
        log.info(sharesMonitor.getSharesCode()+"监控结束");
    }
}
