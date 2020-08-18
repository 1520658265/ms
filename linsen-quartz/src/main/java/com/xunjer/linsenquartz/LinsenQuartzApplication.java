package com.xunjer.linsenquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LinsenQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinsenQuartzApplication.class, args);
    }

}
