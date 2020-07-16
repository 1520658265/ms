package com.xunjer.authservice.service.impl;

import com.xunjer.authservice.entity.Test;
import com.xunjer.authservice.repository.TestRepository;
import com.xunjer.authservice.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author yuansheng
 * @Title: TestServiceImpl
 * @Description:
 * @date 2020/7/1614:09
 */
@Service
public class TestServiceImpl implements ITestService {

    private static Object object = new Object();

    @Autowired
    TestRepository testRepository;

    @Override
    public Boolean simpleUpdate(Integer id) {
        int result = testRepository.updateTT(id);
        return result>0;
    }

    @Override
    public Boolean lockUpdate(Integer id) {
        synchronized (object){
            int result =  testRepository.updateTT(id);
            return result>0;
        }

    }


    @Override
    public Test findById(Integer id) {
        return testRepository.findById(id).orElse(null);
    }
}
