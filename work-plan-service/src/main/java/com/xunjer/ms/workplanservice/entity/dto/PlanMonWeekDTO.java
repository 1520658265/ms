package com.xunjer.ms.workplanservice.entity.dto;

import com.xunjer.ms.workplanservice.entity.PlanMaster;
import lombok.Data;

import java.util.List;

/**
 * @author yuansheng
 * @Title: 根据年份导出月周计划
 * @Description:
 * @date 2020/8/1817:24
 */
@Data
public class PlanMonWeekDTO extends PlanMaster {

    private List<PlanMaster> weekPlan;

}
