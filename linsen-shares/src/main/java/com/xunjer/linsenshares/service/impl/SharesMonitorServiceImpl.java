package com.xunjer.linsenshares.service.impl;

import com.xunjer.linsencommon.utils.StringArrayTransformUtil;
import com.xunjer.linsencommon.utils.StringSplitUtils;
import com.xunjer.linsenshares.common.ResultModel;
import com.xunjer.linsenshares.entity.SharesMonitor;
import com.xunjer.linsenshares.repository.SharesMonitorRepository;
import com.xunjer.linsenshares.service.ISharesMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1717:58
 */
@Service
public class SharesMonitorServiceImpl implements ISharesMonitorService {

    @Autowired
    private SharesMonitorRepository sharesMonitorRepository;

    @Override
    public ResultModel<Boolean> addSharesMonitor(SharesMonitor sharesMonitor) {
        SharesMonitor save = sharesMonitorRepository.save(sharesMonitor);
        return new ResultModel<>(save.getMonitorId()!=null);
    }

    @Override
    public ResultModel<Boolean> updateSharesMonitor(SharesMonitor sharesMonitor) {
        sharesMonitorRepository.save(sharesMonitor);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> deleteSharesMonitor(Integer monitorId) {
        sharesMonitorRepository.deleteById(monitorId);
        return new ResultModel<>(true);
    }

    @Override
    public ResultModel<Boolean> batchDelete(String monitorIds) {
        String[] arrayString = StringSplitUtils.splitByComma(monitorIds);
        int[] intArrays = StringArrayTransformUtil.transformIntArray(arrayString);
        sharesMonitorRepository.deleteByMonitorIdIn(intArrays);
        return new ResultModel<>(true);
    }
}
