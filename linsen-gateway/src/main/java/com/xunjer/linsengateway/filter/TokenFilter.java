package com.xunjer.linsengateway.filter;

import com.xunjer.linsencommon.dictionary.CommonKey;
import org.hibernate.validator.constraints.URL;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author yuansheng
 * @Title: 鉴权拦截器
 * @Description:
 * @date 2020/7/1416:14
 */
public class TokenFilter implements GlobalFilter, Ordered {

    private static final String userLogin = "/user/login";

    /**
     * 要求把token放置请求头里面
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(CommonKey.token);
        String path = exchange.getRequest().getURI().getPath();
        InetSocketAddress ip = exchange.getRequest().getRemoteAddress();
        String ipAddress = exchange.getRequest().getHeaders().getFirst("x-forwarded-for");
        if(!checkFilter(path)){
            if (token == null || token.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }else{
            //这里检测token的正确性
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }


    public boolean checkFilter(String path){
        return path.equalsIgnoreCase(userLogin);
    }
}
