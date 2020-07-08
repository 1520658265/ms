package com.xunjer.ms.workplanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.xunjer.ms.workplanservice.entity"})
public class WorkPlanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkPlanServiceApplication.class, args);
    }

}
