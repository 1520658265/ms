package com.xunjer.ms.workplanservice.controller;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanWeek;
import com.xunjer.ms.workplanservice.entity.dto.PlanWeekDTO;
import com.xunjer.ms.workplanservice.service.IWeekPlanService;
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
    private IWeekPlanService weekPlanService;

    /**
     * 周计划接口
     *  1、新增、修改、删除(逻辑删除)
     * @param planWeek
     * @param pageable
     * @return
     */

    @PostMapping("/get")
    public ResultModel<PageData<List<PlanWeekDTO>>> getList(PlanWeek planWeek, Pageable pageable){
        return weekPlanService.findWeekPlanByCondition(planWeek,pageable);
    }

    @PostMapping("add")
    public ResultModel<Boolean> addPlanWeek(PlanWeek planWeek){
        return weekPlanService.add(planWeek);
    }

    @PostMapping("update")
    public ResultModel<Boolean> updatePlanWeek(PlanWeek planWeek){
        return weekPlanService.update(planWeek);
    }


    @GetMapping("/getDetail")
    public ResultModel<PageData<List<PlanWeekDTO>>> getListDetail(PlanWeek planWeek, Pageable pageable){
        return weekPlanService.findWeekPlanByCondition(planWeek,pageable);
    }

    @GetMapping("/delete")
    public ResultModel<Boolean> deletePlans(String weekIds){
        return weekPlanService.delete(weekIds);
    }
}
