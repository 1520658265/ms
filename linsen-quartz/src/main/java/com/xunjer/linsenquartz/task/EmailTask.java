package com.xunjer.linsenquartz.task;

import cn.hutool.core.bean.BeanUtil;
import com.xunjer.linsenquartz.email.EmailService;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1811:19
 */
@Component
@DisallowConcurrentExecution
public class EmailTask implements Job {

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        ScheduleJob scheduleJob = new ScheduleJob();
         BeanUtil.fillBeanWithMap(jobDataMap,scheduleJob,true);
        emailService.sendSimpleMail(scheduleJob.getEmailTo(), scheduleJob.getEmailTitle(),scheduleJob.getEmailContent());
    }
}
