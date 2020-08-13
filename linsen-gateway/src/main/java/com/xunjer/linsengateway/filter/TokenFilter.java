package com.xunjer.linsengateway.filter;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunjer.linsencommon.dictionary.CommonKey;
import com.xunjer.linsencommon.model.CommonUtils;
import com.xunjer.linsencommon.model.ResultModel;
import com.xunjer.linsencommon.model.UserInfo;
import com.xunjer.linsencommon.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.hibernate.validator.constraints.URL;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Arrays;

/**
 * @author yuansheng
 * @Title: 鉴权拦截器
 * @Description:
 * @date 2020/7/1416:14
 */
public class TokenFilter implements GlobalFilter, Ordered {

    private static final String[] userLogin = {"/user/login","/user/tokenCheck","/linsen-shares/demo/getPublicKey"};

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
        //这里内网调用直接pass  不做拦截
        if(!checkFilter(path)){
            if (token == null || token.isEmpty()) {
                ServerHttpResponse response = exchange.getResponse();
                JSONObject message = new JSONObject();
                message.put("status", -11);
                message.put("data", "非法访问");
                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }


    public boolean checkFilter(String path){
        return Arrays.asList(userLogin).stream().filter(s -> s.equalsIgnoreCase(path)).count()>0;
    }
}
