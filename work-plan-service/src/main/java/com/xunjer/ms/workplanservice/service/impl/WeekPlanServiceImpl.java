package com.xunjer.ms.workplanservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.wenhao.jpa.Specifications;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsencommon.utils.StringArrayTransformUtil;
import com.xunjer.linsencommon.utils.StringSpiltUtil;
import com.xunjer.ms.workplanservice.common.utils.PageTrans;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.entity.dto.WeekPlanDTO;
import com.xunjer.ms.workplanservice.repository.DayPlanRepository;
import com.xunjer.ms.workplanservice.repository.WeekPlayRepository;
import com.xunjer.ms.workplanservice.service.IWeekPlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yuansheng
 * @Title: WeekPlanServiceImpl
 * @Description:
 * @date 2020/7/718:28
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class WeekPlanServiceImpl implements IWeekPlanService {

    @Autowired
    private WeekPlayRepository weekPlayRepository;

    @Autowired
    private DayPlanRepository dayPlanRepository;

    @Override
    public ResultModel<WeekPlan> add(WeekPlan weekPlan) {
        return new ResultModel<>(weekPlayRepository.save(weekPlan));
    }

    @Override
    public ResultModel<WeekPlan> update(WeekPlan weekPlan) {
        return new ResultModel<>(weekPlayRepository.save(weekPlan));
    }

    @Override
    public ResultModel<Boolean> delete(String weekIds) {
        int[] ids = StringArrayTransformUtil.transformIntArray(StringSpiltUtil.spiltByComma(weekIds));
        weekPlayRepository.deleteByWeekIdIn(ids);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<PageData<List<WeekPlan>>> findByCondition(WeekPlan weekPlan, Pageable pageable) {

        Specification<WeekPlan> specification =  Specifications.<WeekPlan> and()
                .like(StringUtils.isNotEmpty(weekPlan.getWeekTitle()),"weekTitle",  weekPlan.getWeekTitle()+"%")
                .between(StringUtils.isNotEmpty(DateUtil.formatDateTime(weekPlan.getWeekStart())),"weekCreateDate",weekPlan.getWeekStart(),weekPlan.getWeekEnd())
                .build();
        Page<WeekPlan> page = weekPlayRepository.findAll(specification, PageRequest.of(0, 15,Sort.by(Sort.DEFAULT_DIRECTION,"weekCreateDate")));
        return new ResultModel<>(PageTrans.trans(page));
    }

    @Override
    public ResultModel<PageData<List<WeekPlanDTO>>> findWeekPlanByCondition(WeekPlan weekPlan, Pageable pageable) {
        Specification<WeekPlan> specification =  Specifications.<WeekPlan> and()
                .like(StringUtils.isNotEmpty(weekPlan.getWeekTitle()),"weekTitle",  weekPlan.getWeekTitle()+"%")
                .between(StringUtils.isNotEmpty(DateUtil.formatDateTime(weekPlan.getWeekStart())),"weekCreateDate",weekPlan.getWeekStart(),weekPlan.getWeekEnd())
                .build();
        Page<WeekPlan> page = weekPlayRepository.findAll(specification, PageRequest.of(0, 15,Sort.by(Sort.DEFAULT_DIRECTION,"weekCreateDate")));
        List<WeekPlan> list = page.getContent();
        List<WeekPlanDTO> dtoList = new ArrayList<>();
        list.forEach(s->{
            WeekPlanDTO weekPlanDTO = new WeekPlanDTO();
            BeanUtils.copyProperties(s,weekPlanDTO);
            weekPlanDTO.setList(dayPlanRepository.findByWeekId(s.getWeekId()));
            dtoList.add(weekPlanDTO);
        });
        PageData<List<WeekPlanDTO>> result = new PageData<>();
        result.setData(dtoList);
        return new ResultModel<>(result);
    }

}
