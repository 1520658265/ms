package com.xunjer.ms.workplanservice.controller;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.dto.PlanMasterDTO;
import com.xunjer.ms.workplanservice.entity.dto.PlanYearDTO;
import com.xunjer.ms.workplanservice.service.IPlanMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuansheng
 * @Title: PlanController
 * @Description:
 * @date 2020/7/718:29
 */
@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    private IPlanMasterService weekPlanService;

    /**
     * 周计划接口
     *  1、新增、修改、删除(逻辑删除)
     * @param planMaster
     * @param pageable
     * @return
     */

    /**
     * 根据年份查询月份数据
     * @param year
     * @return
     */
    @PostMapping("/getYearPlan")
    public ResultModel<PlanYearDTO> getYearPlan(String year){
        return weekPlanService.getYearPlan(year);
    }

    @PostMapping("addPlan")
    public ResultModel<Boolean> addPlanWeek(PlanMaster planMaster){
        return weekPlanService.add(planMaster);
    }

    @PostMapping("updatePlan")
    public ResultModel<Boolean> updatePlanWeek(PlanMaster planMaster){
        return weekPlanService.update(planMaster);
    }

    @GetMapping("/deletePlan")
    public ResultModel<Boolean> deletePlans(String weekIds){
        return weekPlanService.delete(weekIds);
    }
}
