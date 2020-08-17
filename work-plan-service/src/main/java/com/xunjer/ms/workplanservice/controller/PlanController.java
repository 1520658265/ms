package com.xunjer.ms.workplanservice.controller;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.dto.PlanMasterDTO;
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

    @PostMapping("/get")
    public ResultModel<PageData<List<PlanMasterDTO>>> getList(PlanMaster planMaster, Pageable pageable){
        return weekPlanService.findWeekPlanByCondition(planMaster,pageable);
    }

    @PostMapping("add")
    public ResultModel<Boolean> addPlanWeek(PlanMaster planMaster){
        return weekPlanService.add(planMaster);
    }

    @PostMapping("update")
    public ResultModel<Boolean> updatePlanWeek(PlanMaster planMaster){
        return weekPlanService.update(planMaster);
    }


    @GetMapping("/getDetail")
    public ResultModel<PageData<List<PlanMasterDTO>>> getListDetail(PlanMaster planMaster, Pageable pageable){
        return weekPlanService.findWeekPlanByCondition(planMaster,pageable);
    }

    @GetMapping("/delete")
    public ResultModel<Boolean> deletePlans(String weekIds){
        return weekPlanService.delete(weekIds);
    }
}
