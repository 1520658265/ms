package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.entity.dto.WeekPlanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuansheng
 * @Title: IWeekPlanService
 * @Description:
 * @date 2020/7/718:27
 */
public interface IWeekPlanService {

    ResultModel<WeekPlan> add(WeekPlan weekPlan);

    ResultModel<WeekPlan> update(WeekPlan weekPlan);

    ResultModel<Boolean> delete(String weekIds);

    ResultModel<Page<WeekPlan>> findByCondition(WeekPlan weekPlan, Pageable pageable);

    ResultModel<PageData<List<WeekPlanDTO>>> findWeekPlanByCondition(WeekPlan weekPlan, Pageable pageable);
}
