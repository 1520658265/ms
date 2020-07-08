package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    ResultModel<Page<List<WeekPlan>>> findByCoditon(WeekPlan weekPlan, Pageable pageable);
}
