package com.xunjer.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansheng
 * @Title: UserController
 * @Description:
 * @date 2020/7/1417:09
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("test")
    public String text(){
        return "222";
    }
}
