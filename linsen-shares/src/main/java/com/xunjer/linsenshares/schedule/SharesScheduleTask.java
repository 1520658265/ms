package com.xunjer.linsenshares.schedule;


import com.xunjer.linsenshares.common.constant.SharesConstant;
import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.repository.SharesRepository;
import com.xunjer.linsenshares.util.AgentUtils;
import com.xunjer.linsenshares.util.AliDateUtils;
import com.xunjer.linsenshares.util.IpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuansheng
 * @Title: shares的定时任务
 * @Description:
 * @date 2020/8/1216:58
 */
@Component
public class SharesScheduleTask {

    @Autowired
    private SharesRepository sharesRepository;

    @Autowired
    private EmailService emailService;

    /**
     * 数据采集:每个工作日的晚上六点
     */
    @Scheduled(cron = "0 0 19 * * MON-FRI")
    public void shareCollect() throws IOException {
        String url = SharesConstant.url + AliDateUtils.getCurTimestamp();
        Document document = Jsoup.connect(url)
                .userAgent(AgentUtils.randomAgent())
                .referrer(url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .header("X-Forwarded-For", IpUtils.randomIp())
                .get();
        String content = document.body().text();
        content = content.substring(content.indexOf("\"")+1,content.lastIndexOf("\""));
        String[] array = content.split("\",\"");
        Date date = new Date();
        int error = 0;
        List<String> errorCode = new ArrayList<>();
        for(String s : array){
            String[] l = s.split(",");
            System.out.println(l.length);
            if(l.length<17){
                error++;
                errorCode.add(l[1]);
                continue;
            }
            Shares shares = new Shares();
            shares.setSharesCode(l[1]);
            shares.setSharesName(l[2]);
            shares.setNewPrice(l[3]);
            shares.setScopeQuota(l[5]);
            shares.setScopeRate(l[4]);
            shares.setTurnover(l[6]);
            shares.setTurnoverQuota(l[7]);
            shares.setAmplitude(l[8]);
            shares.setMax(l[9]);
            shares.setMin(l[10]);
            shares.setStartPrice(l[11]);
            shares.setLastDayEnd(l[12]);
            shares.setLmr(l[14]);
            shares.setChangeRat(l[15]);
            shares.setPer("");
            shares.setPbr("");
            shares.setCurDate(date);
            sharesRepository.save(shares);
        }
        String emailContent = "数据采集完成"+(array.length-error)+";失败数据："+error;
        emailService.sendSimpleMail("2101849546@qq.com","数据采集",emailContent);
    }
}
