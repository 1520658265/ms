package com.xunjer.ms.workplanservice.service;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.dto.PlanMasterDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yuansheng
 * @Title: IWeekPlanService
 * @Description:
 * @date 2020/7/718:27
 */
public interface IPlanMasterService {

    ResultModel<Boolean> add(PlanMaster planMaster);

    ResultModel<Boolean> update(PlanMaster planMaster);

    ResultModel<Boolean> delete(String weekIds);

    ResultModel<PageData<List<PlanMasterDTO>>> findWeekPlanByCondition(PlanMaster planMaster, Pageable pageable);

}
