package com.xunjer.linsenshares.service.deal.simple;

import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.entity.ds.SingleDealResult;
import com.xunjer.linsenshares.util.SharesUtils;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author yuansheng
 * @Title: 单支分析
 * @Description:
 * @date 2020/8/1415:20
 */
public class SingleDeal implements Callable<SingleDealResult> {

    private String sharesCode;
    private List<Shares> list;

    public SingleDeal(String sharesCode,List<Shares> list){
        this.sharesCode = sharesCode;
        this.list = list;
    }

    @Override
    public SingleDealResult call() throws Exception {
        SingleDealResult result = new SingleDealResult();
        float averageLmr = (float) list.stream().mapToDouble(s-> SharesUtils.checkString(s.getLmr()) ? Float.parseFloat(s.getLmr()) : 0).average().getAsDouble();
        float averageScopeRate = (float) list.stream().mapToDouble(s->Float.parseFloat(s.getScopeRate())).average().getAsDouble();
        float resultF = averageLmr * 0.4F + averageScopeRate*0.6F;
        result.setSumUp(resultF>0 ? 1 : 0);
        result.setSumUpRat(resultF);
        return result;
    }
}
