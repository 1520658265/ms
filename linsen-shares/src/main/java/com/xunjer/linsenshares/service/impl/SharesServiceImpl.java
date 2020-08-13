package com.xunjer.linsenshares.service.impl;

import com.xunjer.linsenshares.common.CommonUtil;
import com.xunjer.linsenshares.common.ResultModel;
import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.repository.SharesRepository;
import com.xunjer.linsenshares.service.ISharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1310:15
 */
@Service
public class SharesServiceImpl implements ISharesService {

    @Autowired
    private SharesRepository sharesRepository;

    @Override
    public ResultModel<Boolean> addShares(Shares shares) {
        sharesRepository.save(shares);
        return CommonUtil.Success();
    }
}
