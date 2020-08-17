package com.xunjer.ms.workplanservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.wenhao.jpa.Specifications;
import com.xunjer.linsencommon.dictionary.Dictionary;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsencommon.utils.StringArrayTransformUtil;
import com.xunjer.linsencommon.utils.StringSplitUtils;
import com.xunjer.ms.workplanservice.entity.PlanMaster;
import com.xunjer.ms.workplanservice.entity.dto.PlanMasterDTO;
import com.xunjer.ms.workplanservice.repository.PlanDayRepository;
import com.xunjer.ms.workplanservice.repository.PlayMasterRepository;
import com.xunjer.ms.workplanservice.service.IPlanMasterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansheng
 * @Title: WeekPlanServiceImpl
 * @Description:
 * @date 2020/7/718:28
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class PlanMasterServiceImpl implements IPlanMasterService {

    @Autowired
    private PlayMasterRepository playMasterRepository;

    @Autowired
    private PlanDayRepository planDayRepository;

    @Override
    public ResultModel<Boolean> add(PlanMaster planMaster) {
        PlanMaster result = playMasterRepository.save(planMaster);
        return new ResultModel<>(result.getMasterId()!=null);
    }

    @Override
    public ResultModel<Boolean> update(PlanMaster planMaster) {
        playMasterRepository.save(planMaster);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> delete(String weekIds) {
        int[] ids = StringArrayTransformUtil.transformIntArray(StringSplitUtils.splitByComma(weekIds));
        playMasterRepository.batchLogicDelete(Dictionary.DeleteFlag.Delete.key(),ids);
        return new ResultModel<>(true);
    }


    @Override
    public ResultModel<PageData<List<PlanMasterDTO>>> findWeekPlanByCondition(PlanMaster planMaster, Pageable pageable) {
        Specification<PlanMaster> specification =  Specifications.<PlanMaster> and()
                .like(StringUtils.isNotEmpty(planMaster.getPlanTitle()),"weekTitle",  planMaster.getPlanTitle()+"%")
                .between(StringUtils.isNotEmpty(DateUtil.formatDateTime(planMaster.getPlanStart())),"weekCreateDate", planMaster.getPlanStart(), planMaster.getPlanEnd())
                .build();
        Page<PlanMaster> page = playMasterRepository.findAll(specification, PageRequest.of(0, 15,Sort.by(Sort.DEFAULT_DIRECTION,"weekCreateDate")));
        List<PlanMaster> list = page.getContent();
        List<PlanMasterDTO> dtoList = new ArrayList<>();
        list.forEach(s->{
            PlanMasterDTO weekPlanDTO = new PlanMasterDTO();
            BeanUtils.copyProperties(s,weekPlanDTO);
            weekPlanDTO.setList(planDayRepository.findByWeekId(s.getMasterId()));
            dtoList.add(weekPlanDTO);
        });
        PageData<List<PlanMasterDTO>> result = new PageData<>();
        result.setData(dtoList);
        return new ResultModel<>(result);
    }

}
