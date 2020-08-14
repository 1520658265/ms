package com.xunjer.linsenshares.entity.ds;

import lombok.Data;

/**
 * @author yuansheng
 * @Title: 单支处理结果
 * @Description:
 * @date 2020/8/1415:21
 */
@Data
public class SingleDealResult {

    /**
     * 总结
     */
    private Integer sumUp;
    /**
     * 总结发生的概率
     */
    private float sumUpRat;

}
