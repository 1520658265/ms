package com.xunjer.linsenquartz.service;

import com.xunjer.linsencommon.model.AliPage;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsenquartz.entity.ScheduleJob;

import java.util.List;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1810:28
 */
public interface IJobService {

    void initSchedule();

    ResultModel<Boolean> addJob(ScheduleJob job);

    ResultModel<Boolean> removeJob(Integer jobId);

    ResultModel<Boolean> pauseJob(Integer jobId, boolean pauseState);

    ResultModel<Boolean> updateJob(ScheduleJob job);

    ResultModel<PageData<List<ScheduleJob>>> find(String jobName, AliPage aliPage);
}
