package com.xunjer.ms.workplanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: DayPlanq
 * @Description: 日计划
 * @date 2020/7/818:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class DayPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dayId;

    private Integer weekId;

    private Date dayDate;

    private String dayContent;

    private Integer dayState;

    private Integer dayScore;

    private String dayDesc;

}
