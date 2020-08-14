package com.xunjer.linsenshares.service.deal;

import java.util.concurrent.*;

/**
 * @author yuansheng
 * @Title: 线程处理单例类
 * @Description:
 * @date 2020/8/1414:46
 */
public class SharesThreadPool {

    public ThreadPoolExecutor sharesPool = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors()+1,
            5L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>()
    );

    private static SharesThreadPool sharesThreadPool = new SharesThreadPool();

    private SharesThreadPool(){}

    public static SharesThreadPool getInstance(){
        return sharesThreadPool;
    }
}
