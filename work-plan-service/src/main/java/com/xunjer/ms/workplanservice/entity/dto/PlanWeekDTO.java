package com.xunjer.ms.workplanservice.entity.dto;

import com.xunjer.ms.workplanservice.entity.PlanDay;
import com.xunjer.ms.workplanservice.entity.PlanWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansheng
 * @Title: WeekPlanDTO
 * @Description: 周计划（包含日计划）详情
 * @date 2020/7/818:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanWeekDTO extends PlanWeek {

    private List<PlanDay> list = new ArrayList<>();
}
