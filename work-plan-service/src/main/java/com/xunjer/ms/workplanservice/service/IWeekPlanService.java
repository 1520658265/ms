package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanWeek;
import com.xunjer.ms.workplanservice.entity.dto.PlanWeekDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yuansheng
 * @Title: IWeekPlanService
 * @Description:
 * @date 2020/7/718:27
 */
public interface IWeekPlanService {

    ResultModel<Boolean> add(PlanWeek planWeek);

    ResultModel<Boolean> update(PlanWeek planWeek);

    ResultModel<Boolean> delete(String weekIds);

    ResultModel<PageData<List<PlanWeekDTO>>> findWeekPlanByCondition(PlanWeek planWeek, Pageable pageable);

}
