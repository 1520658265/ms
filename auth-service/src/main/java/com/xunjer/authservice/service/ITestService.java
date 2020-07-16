package com.xunjer.authservice.service;

import com.xunjer.authservice.entity.Test;

/**
 * @author yuansheng
 * @Title: ITestService
 * @Description:
 * @date 2020/7/1614:09
 */
public interface ITestService {

    Boolean simpleUpdate(Integer id);

    Boolean lockUpdate(Integer id);

    Test findById(Integer id);
}
