package com.xunjer.linsenshares.util;

/**
 * @author yuansheng
 * @Title: 自定义时间工具类
 * @Description:
 * @date 2020/8/1217:02
 */
public class AliDateUtils {

    public static int getCurTimestamp(){
        return (int)(System.currentTimeMillis()/1000L);
    }
}
