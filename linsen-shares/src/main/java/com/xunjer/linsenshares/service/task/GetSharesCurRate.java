package com.xunjer.linsenshares.service.task;


import com.xunjer.linsenshares.entity.Shares;
import com.xunjer.linsenshares.util.SharesUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title: 简单处理最近几天的数据
 * @Description:
 * @date 2020/8/179:14
 */
@Slf4j
public class GetSharesCurRate implements Callable<GetSharesCurRate.Result> {

    private int days;

    private List<Shares> list;

    private List<Date> dates;

    public GetSharesCurRate(int days, List<Shares> list) {
        this.days = days;
        this.list = list;
    }

    @Override
    public Result call() throws Exception {
        log.info("开始进行简单处理"+Thread.currentThread().getName());
        Map<Date,List<Shares>> map = list.stream()
                .collect(Collectors.groupingBy(Shares::getCurDate));
        List<List<Shares>> mList = new ArrayList<>();
        map.forEach((k,v)->{
            mList.add(v);
        });
        List<String>  resultList =  SharesUtils.getSameData(mList);

        Result result = new Result();
        result.setDays(days);
        result.setList(resultList);
        return result;
    }

    @Data
    public static class Result{
        private int days;
        private List<String> list;
    }
}
