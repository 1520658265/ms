package com.xunjer.ms.workplanservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author yuansheng
 * @Title: WeekPlan
 * @Description: 周计划
 * @date 2020/7/718:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class WeekPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer weekId;

    private String weekTitle;

    private String weekContent;

    private Date weekStart;

    private Date weekEnd;

    private Date weekCreateDate;

    private String weekDesc;

    private String weekAppend;

    private Integer weekScore;

    private Integer weekState;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "week_id")
//    private List<DayPlan> list;
}
