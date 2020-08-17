package com.xunjer.linsenshares.util;

import cn.hutool.core.util.EscapeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuansheng
 * @Title: 自定义时间工具类
 * @Description:
 * @date 2020/8/1217:02
 */
public class AliDateUtils {

    public static int getCurTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * 获得今天的固定时间的时间戳
     * @param s HH:mm:hh
     * @return
     */
    public static long getTimestamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDate = simpleDateFormat.format(new Date());
        String resultDateString = curDate.substring(0,curDate.indexOf(" ")+1) + s;
        return simpleDateFormat.parse(resultDateString).getTime();
    }
}
