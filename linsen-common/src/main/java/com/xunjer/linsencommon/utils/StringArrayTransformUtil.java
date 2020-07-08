package com.xunjer.linsencommon.utils;

/**
 * @author yuansheng
 * @Title: StringArrayTransformUtil
 * @Description:
 * @date 2020/7/718:52
 */
public class StringArrayTransformUtil {

    public static int[] transformIntArray(String[] s){
        int[] result = new int[s.length];
        for(int i=0;i<s.length;i++){
            try{
                result[i] = Integer.parseInt(s[i]);
            }catch (Exception e){
                e.printStackTrace();
                return new int[0];
            }
        }
        return result;
    }
}
