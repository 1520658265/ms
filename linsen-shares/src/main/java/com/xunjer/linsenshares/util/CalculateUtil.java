package com.xunjer.linsenshares.util;

import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * @author yuansheng
 * @Title: CalculateUtil
 * @Description: 计算工具类
 * @date 2020/7/317:06
 */
public class CalculateUtil {

    /**
     * 获得平均数
     * @param list
     * @return
     */
    public static double getListAverage(List<? extends Number> list){
        return calculateList(list).getAverage();
    }

    /**
     * 获得总和
     * @param list
     * @return
     */
    public static double getListSum(List<? extends Number> list){
        return calculateList(list).getSum();
    }

    /**
     * 获得最大值
     * @param list
     * @return
     */
    public static double getListMax(List<? extends Number> list){
        return calculateList(list).getMax();
    }

    /**
     * 获得最小值
     * @param list
     * @return
     */
    public static double getListMin(List<? extends Number> list){
        return calculateList(list).getMin();
    }

    /**
     * 获得list的统计类
     * @param list
     * @return
     */
    public static DoubleSummaryStatistics calculateList(List<? extends Number> list){
        return list.stream().mapToDouble(s->Double.parseDouble(s.toString())).summaryStatistics();
    }
}
