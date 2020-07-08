package com.xunjer.ms.workplanservice.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.DayPlan;

import java.util.List;

/**
 * @author yuansheng
 * @Title: IDayPlanService
 * @Description:
 * @date 2020/7/818:13
 */
public interface IDayPlanService {

    ResultModel<Boolean> addBatch(List<DayPlan>list);

    ResultModel<Boolean> addOne(DayPlan dayPlan);

    ResultModel<Boolean> deleteBatch(int[] dayIds);

    ResultModel<Boolean> update(DayPlan dayPlan);

}
