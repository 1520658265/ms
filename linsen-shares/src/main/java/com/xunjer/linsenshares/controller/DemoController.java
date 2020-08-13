package com.xunjer.linsenshares.controller;


import com.xunjer.linsenshares.common.annotation.LogDemo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class DemoController {
    @Value("${publicKey}")
    private String public_key;

    @Value("${privateKey}")
    private String private_key;

    @GetMapping("text")
    public String demo(){
        return "服务正在运行..";
    }

    @GetMapping("getPublicKey")
    public String getPublicKey(){
        return public_key;
    }

    @GetMapping("getPrivateKey")
    public String getPrivateKey(){
        return private_key;
    }
}
