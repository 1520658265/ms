package com.xunjer.linsenshares.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1318:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharesDealDto {

    private Integer id;

    private String sharesCode;

    private String sharesName;

    private String newPrice;

    private String scopeRate;

    private String scopeQuota;
    /**
     * 成交量
     */
    private String turnover;
    /**
     * 成交额
     */
    private String turnoverQuota;
    /**
     * 振幅
     */
    private String amplitude;

    /**
     * 量比
     */
    private String lmr;

    /**
     * 换手率
     */
    private String changeRat;

    private Date curDate;
}
