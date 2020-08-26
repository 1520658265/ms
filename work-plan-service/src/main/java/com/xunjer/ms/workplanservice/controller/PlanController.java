package com.xunjer.ms.workplanservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xunjer.linsencommon.dictionary.Dictionary;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.PlanYear;
import com.xunjer.ms.workplanservice.entity.dto.PlanMasterDTO;
import com.xunjer.ms.workplanservice.entity.dto.PlanYearDTO;
import com.xunjer.ms.workplanservice.service.IPlanMasterService;
import com.xunjer.ms.workplanservice.service.IPlanYearService;
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
@DefaultProperties(defaultFallback = "common")
public class PlanController {

    @Autowired
    private IPlanMasterService weekPlanService;

    @Autowired
    private IPlanYearService iPlanYearService;

    /**
     * 年度接口 : 增加 修改 删除
     */
    @PostMapping("/addYearPlan")
    public ResultModel<Boolean> addYearPlan(PlanYear planYear){
        return iPlanYearService.addYearPlan(planYear);
    }

    @PostMapping("/editYearPlan")
    public ResultModel<Boolean> editYearPlan(PlanYear planYear){
        return iPlanYearService.editYearPlan(planYear);
    }

    @PostMapping("/deleteYearPlan")
    public ResultModel<Boolean> deleteYearPlan(Integer yearId){
        return iPlanYearService.deleteYearPlan(yearId);
    }

    @PostMapping("/getYearPlan")
    @HystrixCommand(fallbackMethod = "fail",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })
    public ResultModel<PlanYearDTO> getYearPlan(String year){
        return weekPlanService.getYearPlan(year);
    }


    /*****************************************月/周计划接口 : 增加 修改 删除*******************************************/
    @PostMapping("addMasterPlan")
    @HystrixCommand
    public ResultModel<Boolean> addMasterPlan(PlanMaster planMaster){
        return weekPlanService.add(planMaster);
    }

    @PostMapping("editMasterPlan")
    public ResultModel<Boolean> updatePlanWeek(PlanMaster planMaster){
        return weekPlanService.update(planMaster);
    }

    @GetMapping("/deleteMasterPlan")
    public ResultModel<Boolean> deletePlans(String weekIds){
        return weekPlanService.delete(weekIds);
    }


    /*************************************************服务兜底方法*************************************************/
    public ResultModel<PlanYearDTO> fail(String year){
        return new ResultModel<>(null, Dictionary.ReturnCode.Error.getKey(),"报错");
    }

    public ResultModel<Boolean> common(PlanMaster planMaster){
        return new ResultModel<>(false,Dictionary.ReturnCode.Error.getKey(),"执行失败");
    }
}
