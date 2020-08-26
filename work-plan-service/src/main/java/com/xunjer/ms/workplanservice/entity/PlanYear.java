package com.xunjer.ms.workplanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author yuansheng
 * @Title: 年度计划实体
 * @Description:
 * @date 2020/8/2617:49
 */
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class PlanYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer yearId;

    private String year;

    private String yearTarget;

    private String yearSummary;

    private String yearScore;

    private Integer yearState;
}
