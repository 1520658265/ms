package com.xunjer.linsenquartz.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.wenhao.jpa.Specifications;
import com.xunjer.linsencommon.model.AliPage;
import com.xunjer.linsencommon.model.CommonUtils;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsenquartz.config.QuartzManager;
import com.xunjer.linsenquartz.entity.ScheduleJob;
import com.xunjer.linsenquartz.repository.JobRepository;
import com.xunjer.linsenquartz.service.IJobService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1810:42
 */
@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void initSchedule() {
        // 这里获取任务信息数据
        List<ScheduleJob> jobList = jobRepository.findAll();
        jobList.forEach(s->{
            if(!s.isPauseFlag() && !s.isDeleteFlag()){
                quartzManager.addJob(s);
            }
        });
    }

    @Override
    public ResultModel<Boolean> addJob(ScheduleJob job) {
        jobRepository.save(job);
        return new ResultModel<>(job.getJobId()>0);
    }

    @Override
    public ResultModel<Boolean> removeJob(Integer jobId) {
        jobRepository.deleteById(jobId);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> pauseJob(Integer jobId,boolean pauseState) {
        jobRepository.updatePauseState(jobId,pauseState);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> updateJob(ScheduleJob job) {
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<PageData<List<ScheduleJob>>> find(String jobName, AliPage aliPage) {
        Specification<ScheduleJob> specification =  Specifications.<ScheduleJob> and()
                .like(StringUtils.isNotEmpty(jobName),"jobName",  "%"+jobName+"%")
                .build();
        Page<ScheduleJob> page = jobRepository.findAll(specification, PageRequest.of(aliPage.getCurPage(), aliPage.getPageSize(), Sort.by(Sort.DEFAULT_DIRECTION,"createDate")));
        List<ScheduleJob> list = page.getContent();
        int total = (int)page.getTotalElements();
        PageData<List<ScheduleJob>> result = new PageData<>();
        result.setData(list);
        result.setTotal(total);
        return new ResultModel<>(result);
    }
}
