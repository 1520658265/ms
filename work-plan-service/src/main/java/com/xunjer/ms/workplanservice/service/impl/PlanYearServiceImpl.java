package com.xunjer.ms.workplanservice.service.impl;

import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.PlanYear;
import com.xunjer.ms.workplanservice.repository.PlanMasterRepository;
import com.xunjer.ms.workplanservice.repository.PlanYearRepository;
import com.xunjer.ms.workplanservice.service.IPlanYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/2617:57
 */
@Service
public class PlanYearServiceImpl implements IPlanYearService {

    @Autowired
    private PlanYearRepository planYearRepository;

    @Autowired
    private PlanMasterRepository planMasterRepository;

    @Override
    public ResultModel<Boolean> addYearPlan(PlanYear planYear) {
        planYearRepository.save(planYear);
        return new ResultModel<>(planYear.getYearId()>0);
    }

    @Override
    public ResultModel<Boolean> editYearPlan(PlanYear planYear) {
        planYearRepository.save(planYear);
        return new ResultModel<>();
    }

    @Override
    public ResultModel<Boolean> deleteYearPlan(Integer yearId) {
        PlanYear planYear = planYearRepository.findById(yearId).get();
        planYearRepository.deleteById(yearId);
        //删除月计划 周计划 日计划 （其实只要 删除月计划就可以了）
        planMasterRepository.deleteByParentId(yearId);
        List<PlanMaster> monthList = planMasterRepository.findByParentId(yearId);
        List<Integer> monthIds = monthList.stream().map(PlanMaster::getMasterId).collect(Collectors.toList());

        return new ResultModel<>();
    }
}
