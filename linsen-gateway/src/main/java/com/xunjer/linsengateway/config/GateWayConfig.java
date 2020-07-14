package com.xunjer.linsengateway.config;

import com.xunjer.linsengateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuansheng
 * @Title: 网关配置
 * @Description:
 * @date 2020/7/1416:12
 */
@Configuration
public class GateWayConfig {

    @Bean
    public TokenFilter tokenFilter(){
        return new com.xunjer.linsengateway.filter.TokenFilter();
    }
}
