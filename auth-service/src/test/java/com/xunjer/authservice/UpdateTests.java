package com.xunjer.authservice;

import com.xunjer.authservice.service.ITestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    /**
     * 200次的执行，最终实验有77次执行成功
     * 确实是执行了200次但是数据丢失了
     */
    @Test
    void simpleUpdate(){
        for (int i = 0; i < 200 ; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Boolean result = iTestService.simpleUpdate(1);
                    System.out.println("simpleUpdate"+result);
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
    void lockUpdate(){
        for (int i = 0; i < 200 ; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Boolean result = iTestService.lockUpdate(1);
                    System.out.println("lockUpdate"+result);
                }
            });
        }
        com.xunjer.authservice.entity.Test test = iTestService.findById(1);
        System.out.println(test.getTt());
        System.out.println("简单测试运行完成");
    }
}
