package com.xunjer.linsenshares.common;


import com.xunjer.linsenshares.common.dictionary.Dictionary;

/**
 * @author yuansheng
 * @Title: CommonUtil
 * @Description:
 * @date 2020/7/115:16
 */
public class CommonUtil {

    public static ResultModel<Boolean> Success(){
        return new ResultModel(true, Dictionary.ReturnCode.Success.getKey(),"成功");
    }

    public static ResultModel<Boolean> NotLogin(){
        return new ResultModel(false,Dictionary.ReturnCode.UnLogin.getKey(),"没有登录");
    }

    public static ResultModel<Boolean> NotAuth(){
        return new ResultModel(false,Dictionary.ReturnCode.UnAuth.getKey(),"没有权限");
    }
}
