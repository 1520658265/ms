package com.xunjer.ms.workplanservice.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.wenhao.jpa.Specifications;
import com.xunjer.linsencommon.dictionary.Dictionary;
import com.xunjer.linsencommon.model.PageData;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsencommon.utils.StringArrayTransformUtil;
import com.xunjer.linsencommon.utils.StringSpiltUtil;
import com.xunjer.ms.workplanservice.entity.PlanWeek;
import com.xunjer.ms.workplanservice.entity.dto.PlanWeekDTO;
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
public class WeekPlanServiceImpl implements IWeekPlanService {

    @Autowired
    private WeekPlayRepository weekPlayRepository;

    @Autowired
    private DayPlanRepository dayPlanRepository;

    @Override
    public ResultModel<Boolean> add(PlanWeek planWeek) {
        PlanWeek result = weekPlayRepository.save(planWeek);
        return new ResultModel<>(result.getWeekId()!=null);
    }

    @Override
    public ResultModel<Boolean> update(PlanWeek planWeek) {
        weekPlayRepository.save(planWeek);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> delete(String weekIds) {
        int[] ids = StringArrayTransformUtil.transformIntArray(StringSpiltUtil.spiltByComma(weekIds));
        weekPlayRepository.batchLogicDelete(Dictionary.DeleteFlag.Delete.key(),ids);
        return new ResultModel<>(true);
    }


    @Override
    public ResultModel<PageData<List<PlanWeekDTO>>> findWeekPlanByCondition(PlanWeek planWeek, Pageable pageable) {
        Specification<PlanWeek> specification =  Specifications.<PlanWeek> and()
                .like(StringUtils.isNotEmpty(planWeek.getWeekTitle()),"weekTitle",  planWeek.getWeekTitle()+"%")
                .between(StringUtils.isNotEmpty(DateUtil.formatDateTime(planWeek.getWeekStart())),"weekCreateDate", planWeek.getWeekStart(), planWeek.getWeekEnd())
                .build();
        Page<PlanWeek> page = weekPlayRepository.findAll(specification, PageRequest.of(0, 15,Sort.by(Sort.DEFAULT_DIRECTION,"weekCreateDate")));
        List<PlanWeek> list = page.getContent();
        List<PlanWeekDTO> dtoList = new ArrayList<>();
        list.forEach(s->{
            PlanWeekDTO weekPlanDTO = new PlanWeekDTO();
            BeanUtils.copyProperties(s,weekPlanDTO);
            weekPlanDTO.setList(dayPlanRepository.findByWeekId(s.getWeekId()));
            dtoList.add(weekPlanDTO);
        });
        PageData<List<PlanWeekDTO>> result = new PageData<>();
        result.setData(dtoList);
        return new ResultModel<>(result);
    }

}
