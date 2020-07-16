package com.xunjer.linsencommon.model;

import com.xunjer.linsencommon.dictionary.Dictionary;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/7/1618:24
 */
public class CommonUtils<T> {

    public static ResultModel notAuth(){
        return new ResultModel(false, Dictionary.ReturnCode.UnAuth.getKey(),"没有权限");
    }

    public static ResultModel tokenValid(){
        return new ResultModel(false, Dictionary.ReturnCode.TokenValid.getKey(),"鉴权信息无效");
    }
}
