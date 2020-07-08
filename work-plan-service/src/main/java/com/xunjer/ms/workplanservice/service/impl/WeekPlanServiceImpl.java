package com.xunjer.ms.workplanservice.service.impl;

import com.github.wenhao.jpa.Specifications;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsencommon.utils.StringArrayTransformUtil;
import com.xunjer.linsencommon.utils.StringSpiltUtil;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import com.xunjer.ms.workplanservice.repository.WeekPlayRepository;
import com.xunjer.ms.workplanservice.service.IWeekPlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
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
public class WeekPlanServiceImpl implements IWeekPlanService {

    @Autowired
    private WeekPlayRepository weekPlayRepository;

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
        return new ResultModel<>(weekPlayRepository.deleteByWeekId(ids)>0);
    }

    @Override
    public ResultModel<Page<List<WeekPlan>>> findByCoditon(WeekPlan weekPlan, Pageable pageable) {

        Specification<WeekPlan> specification =  Specifications.<WeekPlan> and()
                .like("weekTitle",  weekPlan.getWeekTitle()+"%")
                .build();
        SpecificationFactory
        return new ResultModel<>(weekPlayRepository.findAll(specification, PageRequest.of(0, 15,Sort.by(Sort.DEFAULT_DIRECTION,"week_create_date"))));
    }

}
