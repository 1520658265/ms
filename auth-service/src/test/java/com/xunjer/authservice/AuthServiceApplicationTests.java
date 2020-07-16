package com.xunjer.authservice;

import com.xunjer.authservice.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class AuthServiceApplicationTests {

    @Autowired
    TestRepository testRepository;

    ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Test
    void contextLoads() {
        for (int i = 0; i < 200 ; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    testRepository.updateTT(1);
                    System.out.println(num);
                }
            });
        }
        com.xunjer.authservice.entity.Test test = testRepository.findById(1).orElse(new com.xunjer.authservice.entity.Test());
        System.out.println(test.getTt());
    }

    @Test
    void terte(){
        for (int i = 0; i < 200 ; i++) {
            int num = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    com.xunjer.authservice.entity.Test test = testRepository.findById(1).orElse(new com.xunjer.authservice.entity.Test());
                    test.setTt(test.getTt()+1);
                    testRepository.save(test);
                    System.out.println(num);
                }
            });
        }
        com.xunjer.authservice.entity.Test test1 = testRepository.findById(1).orElse(new com.xunjer.authservice.entity.Test());
        System.out.println(test1.getTt());
    }

}
