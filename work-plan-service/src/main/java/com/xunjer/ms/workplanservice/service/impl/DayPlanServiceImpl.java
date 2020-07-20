package com.xunjer.ms.workplanservice.service.impl;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanDay;
import com.xunjer.ms.workplanservice.repository.DayPlanRepository;
import com.xunjer.ms.workplanservice.service.IDayPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author yuansheng
 * @Title: DayPlanServiceImpl
 * @Description:
 * @date 2020/7/818:18
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class DayPlanServiceImpl implements IDayPlanService {

    @Autowired
    private DayPlanRepository dayPlanRepository;

    @Override
    public ResultModel<Boolean> addBatch(List<PlanDay> list) {
        List<PlanDay> save = dayPlanRepository.saveAll(list);
        return new ResultModel<>(Boolean.TRUE);
    }

    @Override
    public ResultModel<Boolean> addOne(PlanDay planDay) {
        dayPlanRepository.save(planDay);
        return new ResultModel<>(Boolean.TRUE);
    }

    @Override
    public ResultModel<Boolean> deleteBatch(int[] dayIds) {
        dayPlanRepository.deleteByDayIdIn(dayIds);
        return new ResultModel<>(Boolean.TRUE);
    }

    @Override
    public ResultModel<Boolean> update(PlanDay planDay) {
        dayPlanRepository.save(planDay);
        return new ResultModel<>(Boolean.TRUE);
    }
}
