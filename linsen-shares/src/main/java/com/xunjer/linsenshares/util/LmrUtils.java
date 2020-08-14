package com.xunjer.linsenshares.util;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1415:33
 */
public class LmrUtils {

    public static float dealLmrRat(float lmr) throws Exception {
        if(lmr<0.8F){
            return CalculateUtil.floatBetween(-0.2F,0);
        }else if(lmr<=1.5){
            return CalculateUtil.floatBetween(0,0.4F);
        }else if(lmr<=2.5F){
            return CalculateUtil.floatBetween(0.4F,0.8F);
        }else if(lmr<=5){
            return CalculateUtil.floatBetween(0.8F,1F);
        }else{
            return -1F;
        }
    }
}
