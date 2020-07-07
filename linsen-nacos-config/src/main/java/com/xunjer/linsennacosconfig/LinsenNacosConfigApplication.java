package com.xunjer.linsennacosconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LinsenNacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinsenNacosConfigApplication.class, args);
    }

}
