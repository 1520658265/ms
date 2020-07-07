package com.xunjer.ms.workplanservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuansheng
 * @Title: TestController
 * @Description:
 * @date 2020/7/717:19
 */
@RestController
@RefreshScope
public class Controller {

    @Value("${test}")
    private String text;

    @GetMapping("text")
    public String get(){
        return text;
    }
}
