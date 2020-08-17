package com.xunjer.linsencommon.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.IllegalFormatException;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1718:01
 */
public class StringSplitUtils {

    /**
     * 通过分号切割
     * @param s
     * @return
     */
    public static String[] splitBySemicolon(String s){
        if(StringUtils.isEmpty(s)){
            return new String[0];
        }
        return s.split(";");
    }
    /**
     * 通过逗号切割
     * @param s
     * @return
     */
    public static String[] splitByComma(String s ){
        if(StringUtils.isEmpty(s)){
            return new String[0];
        }
        return s.split(",");
    }

}
