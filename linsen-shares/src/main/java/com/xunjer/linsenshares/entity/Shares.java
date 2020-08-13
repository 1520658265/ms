package com.xunjer.linsenshares.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: 股票实体
 * @Description:
 * @date 2020/8/1118:45
 */
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Shares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sharesCode;

    private String sharesName;

    private String newPrice;

    private String scopeRate;

    private String scopeQuota;

    private String turnover;

    private String turnoverQuota;

    private String amplitude;

    private String max;

    private String min;

    private String startPrice;

    private String lastDayEnd;

    private String lmr;

    private String changeRat;

    private String per;

    private String pbr;

    private Date curDate;
}
