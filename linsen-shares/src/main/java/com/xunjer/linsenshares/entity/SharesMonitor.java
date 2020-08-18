package com.xunjer.linsenshares.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: shares监控实体
 * @Description:
 * @date 2020/8/1717:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class SharesMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer monitorId;

    private String sharesCode;

    private String sharesName="";

    private BigDecimal monitorPrice;

    private Date createDate;

    private String result="";

    private Integer monitorTime = 5;
}
