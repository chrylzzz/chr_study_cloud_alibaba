package com.chryl.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 网关鉴权
 * Created by Chr.yl on 2020/6/24.
 *
 * @author Chr.yl
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    //过滤逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String authorization = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(authorization)
                || !StringUtils.equals("admin", authorization)) {//鉴权失败

            ServerHttpResponse response = serverWebExchange.getResponse();
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            //组织响应json
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "401");
            jsonObject.put("message", "没有权限");
            byte[] bytes = JSON.toJSONString(jsonObject).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }

    //配置执行顺序
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
