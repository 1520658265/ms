package com.xunjer.linsenshares.service;

import com.xunjer.linsenshares.common.ResultModel;
import com.xunjer.linsenshares.entity.SharesMonitor;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1717:56
 */
public interface ISharesMonitorService {

    ResultModel<Boolean> addSharesMonitor(SharesMonitor sharesMonitor);

    ResultModel<Boolean> updateSharesMonitor(SharesMonitor sharesMonitor);

    ResultModel<Boolean> deleteSharesMonitor(Integer monitorId);

    ResultModel<Boolean> batchDelete(String monitorIds);
}
