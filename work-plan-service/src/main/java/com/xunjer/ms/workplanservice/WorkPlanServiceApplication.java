package com.xunjer.ms.workplanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@EnableDiscoveryClient

public class WorkPlanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkPlanServiceApplication.class, args);
    }

}
