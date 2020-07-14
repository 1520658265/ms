package com.xunjer.linsengateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LinsenGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinsenGatewayApplication.class, args);
    }

}
