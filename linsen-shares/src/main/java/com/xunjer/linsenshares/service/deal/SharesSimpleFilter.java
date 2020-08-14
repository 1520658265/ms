package com.xunjer.linsenshares.service.deal;

import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.util.SharesUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title: 简单的数据清洗
 * @Description: 按照几个数据进行筛选 涨幅 + 振幅 + 量比 + 还手率
 * @date 2020/8/1414:58
 */
public class SharesSimpleFilter {

    public static List<Shares> filterRise(List<Shares> shares){
        shares.stream().filter(s->
                Float.parseFloat(s.getScopeRate()) > 0 &&
                        (SharesUtils.checkString(s.getLmr()) && Float.parseFloat(s.getLmr()) > 0.8F)
        ).collect(Collectors.toList());
        return shares;
    }
}
