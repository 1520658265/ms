package com.xunjer.linsenquartz.controller;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsenquartz.config.QuartzManager;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1816:20
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    @GetMapping("addTask")
    public ResultModel<Boolean> addTask(ScheduleJob task){
        quartzManager.addJob(task);
        return new ResultModel<>(true);
    }

    @GetMapping("allTask")
    public ResultModel<List<ScheduleJob>> allTask() throws SchedulerException {
        return new ResultModel<>(quartzManager.getAllJob());
    }

    @GetMapping("runningTask")
    public ResultModel<List<ScheduleJob>> runningTask() throws SchedulerException {
        return new ResultModel<>(quartzManager.getRunningJob());
    }

    @GetMapping("pauseTask")
    public ResultModel<Boolean> pauseJob(ScheduleJob pauseTask) throws SchedulerException {
        quartzManager.pauseJob(pauseTask);
        return new ResultModel<>(true);
    }

}
