package com.xunjer.linsenshares.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: TaskWeek
 * @Description: 周任务
 * @date 2020/7/316:56
 */
@Entity
@Table
@Data
public class TaskWeek {
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

}
