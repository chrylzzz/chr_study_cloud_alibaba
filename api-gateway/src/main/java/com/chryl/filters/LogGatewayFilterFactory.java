package com.chryl.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chr.yl on 2020/6/24.
 *
 * @author Chr.yl
 */
//@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    //读取配置文件的内容赋值到config类中
    public List<String> shortcutFieldOrder() {
        //注意与配置文件的顺序一致
        return Arrays.asList("consoleLog", "cacheLog");
    }

    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
                if (config.isConsoleLog()) {
                    System.out.println("缓存日志开启...");
                }
                if (config.isCacheLog()) {
                    System.out.println("控制台日志开启...");
                }
                return gatewayFilterChain.filter(serverWebExchange);
            }
        };
    }

    //配置类,接收配置参数
    @Data//get set
    @NoArgsConstructor//无参
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;

    }
}
