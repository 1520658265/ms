package com.xunjer.ms.workplanservice.controller;

import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.entity.dto.WeekPlanDTO;
import com.xunjer.ms.workplanservice.service.IWeekPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuansheng
 * @Title: WorkController
 * @Description:
 * @date 2020/7/718:29
 */
@RestController
@RequestMapping("plan")
public class WorkController {

    @Autowired
    private IWeekPlanService weekPlanService;

    @GetMapping("/get")
    public ResultModel<Page<WeekPlan>> getList(WeekPlan weekPlan, Pageable pageable){
        return weekPlanService.findByCondition(weekPlan,pageable);
    }

    @GetMapping("/getDetail")
    public ResultModel<PageData<List<WeekPlanDTO>>> getListDetail(WeekPlan weekPlan, Pageable pageable){
        return weekPlanService.findWeekPlanByCondition(weekPlan,pageable);
    }

    @GetMapping("/delete")
    public ResultModel<Boolean> deletePlans(String weekIds){
        return weekPlanService.delete(weekIds);
    }
}
