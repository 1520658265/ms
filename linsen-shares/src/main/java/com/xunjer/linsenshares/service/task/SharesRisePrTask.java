package com.xunjer.linsenshares.service.task;


import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.entity.dto.SharesDealDto;
import com.xunjer.linsenshares.service.task.dd.SharesRisePrTaskResult;
import com.xunjer.linsenshares.util.SharesUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1917:58
 */
@Slf4j
public class SharesRisePrTask implements Callable<SharesRisePrTaskResult> {

    private int step;

    private List<Date> dateList;

    private Map<Date,List<Shares>> mapData;

    public SharesRisePrTask(int step, List<Date> dateList, Map<Date,List<Shares>> mapData) {
        this.step = step;
        this.mapData = mapData;
        this.dateList = dateList;
    }

    @Override
    public SharesRisePrTaskResult call() throws Exception {
        System.out.println("开始处理数据"+Thread.currentThread().getName());
        List<Double> num = new ArrayList<>();
        for(int i=0;i<dateList.size()-step;i++){
            List<List<Shares>> deal = new ArrayList<>();
            for(int j=0;j<step;j++){
               deal.add(mapData.get(dateList.get(j)));
            }
            List<SharesDealDto> sameList = SharesUtils.getSameData(deal);
            List<String> strings = sameList.stream().map(SharesDealDto::getSharesCode).collect(Collectors.toList());
            List<Shares> next = mapData.get(dateList.get(i+step));
            int success = (int) next.stream().filter(s->strings.contains(s.getSharesCode()) && SharesUtils.checkString(s.getScopeQuota()) && Float.parseFloat(s.getScopeQuota())>0).count();
            num.add((double)success/strings.size());
        }

        System.out.print("步长为"+step+"的处理结果是：");
        num.forEach(s-> System.out.print(s));
        System.out.println("平均数据："+ num.stream().mapToDouble(s->s).summaryStatistics().getAverage());
        return null;
    }
}
