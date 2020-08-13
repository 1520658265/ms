package com.xunjer.authservice;

import cn.hutool.core.date.DateUtil;
import com.alibaba.nacos.common.utils.UuidUtils;
import com.xunjer.authservice.entity.UserInfo;
import com.xunjer.authservice.repository.UserInfoRepository;
import com.xunjer.authservice.service.ITestService;
import com.xunjer.authservice.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/7/1614:14
 */
@SpringBootTest
public class UpdateTests {

    ExecutorService executorService = Executors.newFixedThreadPool(50);

    @Autowired
    ITestService iTestService;
    @Autowired
    UserInfoRepository userInfoRepository;

    /**
     * 200次的执行，最终实验有77次执行成功
     * 确实是执行了200次但是数据丢失了
     */
    @Test
    void simpleUpdate() {
        for (int i = 0; i < 200; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Boolean result = iTestService.simpleUpdate(1);
                    System.out.println("simpleUpdate" + result);
                }
            });
        }
        com.xunjer.authservice.entity.Test test = iTestService.findById(1);
        System.out.println(test.getTt());
        System.out.println("简单测试运行完成");
    }

    /**
     * 枷锁只执行了个位数的次数 y
     * 解释：这里确实锁是有用的，数量不对是因为，其它线程并没有抢占到执行的锁，所有就没有执行
     */
    @Test
    void lockUpdate() {
        for (int i = 0; i < 200; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Boolean result = iTestService.lockUpdate(1);
                    System.out.println("lockUpdate" + result);
                }
            });
        }
        com.xunjer.authservice.entity.Test test = iTestService.findById(1);
        System.out.println(test.getTt());
        System.out.println("简单测试运行完成");
    }

    /**
     * 插入100万数据
     */
    @Test
    void addData() {
        List<UserInfo> allList = new ArrayList<>();
        String startDate = "2020-01-01 00:00:00", endDate = "2020-08-07 00:00:00";
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
//            System.out.println(i);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setCreateDate(DateUtils.randomDate(startDate, endDate));
            userInfo.setPassword(UuidUtils.generateUuid().substring(0,7));
            userInfo.setFlagDel(Math.random()>0.5 ? 1 : 0);
            userInfo.setNickName(UuidUtils.generateUuid().substring(0,4));
            userInfo.setUserName(UuidUtils.generateUuid().substring(0,5));
            userInfo.setUserDesc(UuidUtils.generateUuid().substring(0,20));
            allList.add(userInfo);
//            userInfoRepository.save(userInfo);
        }
        for(int i = 0; i < 1000; i++){
            System.out.println("执行到："+i);
            List<UserInfo> tem = allList.stream().skip(i*1000).limit(1000).collect(Collectors.toList());
            userInfoRepository.saveAll(tem);
        }
        long end = System.currentTimeMillis();
        System.out.println("用时" + (end - start));
    }
}
