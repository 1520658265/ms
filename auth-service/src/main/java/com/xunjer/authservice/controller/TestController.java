package com.xunjer.authservice.controller;

import com.xunjer.authservice.service.ITestService;
import com.xunjer.linsencommon.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/7/1616:23
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ITestService iTestService;

    @GetMapping("simple")
    public ResultModel<Boolean> simple(){
        return new ResultModel<>(iTestService.simpleUpdate(1));
    }

    @GetMapping("lock")
    public ResultModel<Boolean> lock(){
        return new ResultModel<>(iTestService.lockUpdate(1));
    }
}
