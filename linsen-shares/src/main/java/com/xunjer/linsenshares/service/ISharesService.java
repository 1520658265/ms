package com.xunjer.linsenshares.service;

import com.xunjer.linsenshares.common.ResultModel;
import com.xunjer.linsenshares.entity.Shares;

/**
 * @author yuansheng
 * @Title: shares的接口
 * @Description:
 * @date 2020/8/1310:14
 */
public interface ISharesService {

    ResultModel<Boolean> addShares(Shares shares);
}
