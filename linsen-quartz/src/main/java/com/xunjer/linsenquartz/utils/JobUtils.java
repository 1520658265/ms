package com.xunjer.linsenquartz.utils;

import cn.hutool.core.bean.BeanUtil;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import org.quartz.JobDataMap;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1816:36
 */
public class JobUtils {

    public static ScheduleJob transform(JobDataMap map){
        ScheduleJob scheduleJob = new ScheduleJob();
        BeanUtil.fillBeanWithMap(map,scheduleJob,true);
        return scheduleJob;
    }
}
