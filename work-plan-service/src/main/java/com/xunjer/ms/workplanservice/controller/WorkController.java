package com.xunjer.ms.workplanservice.controller;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.service.IWeekPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResultModel<Page<List<WeekPlan>>> getList(String weekTitle){
        WeekPlan weekPlan = new WeekPlan();
        weekPlan.setWeekTitle(weekTitle);
        return weekPlanService.findByCoditon(weekPlan,null);
    }
}
