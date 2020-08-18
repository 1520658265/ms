package com.xunjer.linsenquartz.task;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1816:17
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class DemoTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        ScheduleJob scheduleJob = new ScheduleJob();
        BeanUtil.fillBeanWithMap(jobDataMap,scheduleJob,true);
        log.info(scheduleJob.toString());
        System.out.println(DateUtil.formatDateTime(new Date())+"    我在运行哦"+scheduleJob.getJobName());
    }
}
