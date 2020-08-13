package com.xunjer.linsenshares.controller;


import com.xunjer.linsenshares.common.annotation.LogDemo;
import com.xunjer.linsenshares.repository.SharesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansheng
 * @Title: DemoController
 * @Description: 测试Controller
 * @date 2020/6/2818:22
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private SharesRepository sharesRepository;

    @GetMapping("text")
    @LogDemo
    public String demo(){
        return "服务正在运行..";
    }
}
