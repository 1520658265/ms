package com.xunjer.linsenquartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: 定时任务实体
 * @Description:
 * @date 2020/8/1810:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class ScheduleJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String jobName;

    private String jobGroup;

    private String emailTo;

    private String emailTitle;

    private String emailContent;

    private String corn;

    private String description;

    private String beanClass;

    private boolean pauseFlag;

    private boolean deleteFlag;

    private Date createDate;
}
