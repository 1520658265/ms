package com.xunjer.linsenshares.schedule;


import com.xunjer.linsenshares.common.constant.SharesConstant;
import com.xunjer.linsenshares.common.email.EmailService;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.repository.SharesRepository;
import com.xunjer.linsenshares.service.deal.SharesThreadPool;
import com.xunjer.linsenshares.service.task.GetSharesCurRate;
import com.xunjer.linsenshares.service.task.SharesRisePrTask;
import com.xunjer.linsenshares.util.AgentUtils;
import com.xunjer.linsenshares.util.AliDateUtils;
import com.xunjer.linsenshares.util.IpUtils;
import com.xunjer.linsenshares.util.SharesUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
            Shares shares = new Shares();
            shares.setSharesCode(l[1]);
            shares.setSharesName(l[2]);
            shares.setNewPrice(l[3]);
            shares.setScopeQuota(l[4]);
            shares.setScopeRate(l[5]);
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

    /**
     * 每天晚上八点进行简单的数据统计
     */
    @Scheduled(cron = "0 0 20 * * SUN-THU")
    public void simpleDeal(){
        System.out.println("kaishi ");
        List<Shares> all = sharesRepository.findAll();
        all = all.stream().filter(s-> SharesUtils.checkString(s.getScopeQuota()) && Float.parseFloat(s.getScopeQuota())>0).collect(Collectors.toList());
        List<Date> dayList = all.stream().map(Shares::getCurDate).distinct().collect(Collectors.toList());
        List<Future<GetSharesCurRate.Result>> futures = new ArrayList<>();
        int max = dayList.size()-2;
        for(int i=0;i<max;i++){
            List<Date> cur = dayList.subList(0,i+3);
            List<Shares> l = all.stream().filter(s->cur.contains(s.getCurDate())).collect(Collectors.toList());
            futures.add(SharesThreadPool.getInstance().sharesPool.submit(new GetSharesCurRate(i+3,l)));

        }
        StringBuilder sb = new StringBuilder("统计结果："+"\n");
        futures.forEach(f->{
            try {
                GetSharesCurRate.Result result = f.get();
                sb.append("     "+result.getDays()+"天相同的处理结果："+"\n        "+result.getList().stream().collect(Collectors.joining("-"))+"\n");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
        emailService.sendSimpleMail("1520658265@qq.com","处理结果",sb.toString());
    }

    @Scheduled(cron = "0 10 20 * * SUN-THU")
    public void simpleTrain(){
        List<Shares> all = sharesRepository.findAll();
        all = all.stream().filter(s-> SharesUtils.checkString(s.getScopeQuota()) && Float.parseFloat(s.getScopeQuota())>0).collect(Collectors.toList());
        List<Date> dayList = all.stream().map(Shares::getCurDate).distinct().collect(Collectors.toList());
        Map<Date,List<Shares>> map = all.stream()
                .collect(Collectors.groupingBy(Shares::getCurDate));
        int max = dayList.size()-2;
        for(int i=0;i<max;i++){
            SharesThreadPool.getInstance().sharesPool.submit(new SharesRisePrTask(i+2,dayList,map));
        }
    }
}
