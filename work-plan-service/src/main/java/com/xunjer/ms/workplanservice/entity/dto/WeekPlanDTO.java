package com.xunjer.ms.workplanservice.entity.dto;

import com.xunjer.ms.workplanservice.entity.DayPlan;
import com.xunjer.ms.workplanservice.entity.WeekPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
public class WeekPlanDTO extends WeekPlan {

    private List<DayPlan> list = new ArrayList<>();
}
