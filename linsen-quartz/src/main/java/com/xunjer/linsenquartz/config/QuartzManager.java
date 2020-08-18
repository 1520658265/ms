package com.xunjer.linsenquartz.config;

import cn.hutool.core.bean.BeanUtil;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import com.xunjer.linsenquartz.repository.JobRepository;
import com.xunjer.linsenquartz.utils.JobUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1811:08
 */
@Service
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobRepository jobRepository;

    /**
     * 添加任务
     * @param task
     */
    public void addJob(ScheduleJob task) {
        try {
            jobRepository.save(task);
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());
            Map<String, Object> stringObjectMap = BeanUtil.beanToMap(task);
            JobDataMap jobDataMap = new JobDataMap(stringObjectMap);
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobName(), task.getJobGroup())// 任务名称和组构成任务key
                    .setJobData(jobDataMap)//原始数据
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup())// 触发器key
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCorn())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleJob> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobList.add(JobUtils.transform(jobDetail.getJobDataMap()));
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<ScheduleJob> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        System.out.println(executingJobs.size());
        List<ScheduleJob> jobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            jobList.add(JobUtils.transform(executingJob.getMergedJobDataMap()));
        }
        return jobList;
    }

    /**
     * 暂停一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(ScheduleJob task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void resumeJob(ScheduleJob task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param task
     * @throws SchedulerException
     */
    public void deleteJob(ScheduleJob task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     *
     * @param task
     * @throws SchedulerException
     */
    public void runJobNow(ScheduleJob task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param task
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob task) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCorn());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
