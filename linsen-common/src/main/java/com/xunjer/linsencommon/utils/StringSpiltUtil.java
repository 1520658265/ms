package com.xunjer.linsencommon.utils;

import java.util.Arrays;

/**
 * @author yuansheng
 * @Title: StringSpiltUtil
 * @Description:
 * @date 2020/7/718:49
 */
public class StringSpiltUtil {

    public static String[] spiltByComma(String s){
        if(s=="" || s==null){
            return new String[0];
        }else{
            return s.split(",");
        }
    }
}
