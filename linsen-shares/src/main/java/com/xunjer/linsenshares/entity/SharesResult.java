package com.xunjer.linsenshares.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: 处理结果
 * @Description:
 * @date 2020/8/1414:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class SharesResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sharesCode;

    private Date curDate;

    private Boolean result;
}
