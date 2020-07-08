package com.xunjer.ms.workplanservice.entity.dto;

import com.xunjer.ms.workplanservice.entity.DayPlan;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansheng
 * @Title: WeekPlanDTO
 * @Description: 周计划（包含日计划）详情
 * @date 2020/7/818:16
 */
@Data
public class WeekPlanDTO extends WeekPlan {

    private List<DayPlan> list = new ArrayList<>();
}
