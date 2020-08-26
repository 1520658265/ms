package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanYear;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/2617:55
 */
public interface IPlanYearService {

    ResultModel<Boolean> addYearPlan(PlanYear planYear);

    ResultModel<Boolean> editYearPlan(PlanYear planYear);

    ResultModel<Boolean> deleteYearPlan(Integer yearId);

}
