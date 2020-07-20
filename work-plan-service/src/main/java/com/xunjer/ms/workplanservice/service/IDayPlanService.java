package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanDay;

import java.util.List;

/**
 * @author yuansheng
 * @Title: IDayPlanService
 * @Description:
 * @date 2020/7/818:13
 */
public interface IDayPlanService {

    ResultModel<Boolean> addBatch(List<PlanDay>list);

    ResultModel<Boolean> addOne(PlanDay planDay);

    ResultModel<Boolean> deleteBatch(int[] dayIds);

    ResultModel<Boolean> update(PlanDay planDay);

}
