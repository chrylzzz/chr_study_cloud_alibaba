package com.chryl.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言,需要继承 AbstractRoutePredicateFactory<T> , 泛型为该类的Config类, Config类为静态
 * Created by Chr.yl on 2020/6/24.
 *
 * @author Chr.yl
 */
//该断言 限制 18 - 60 年龄的允许访问
//@Component//必须使用spring管理
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件的数据给Config类定义的配置进行赋值
    public List<String> shortcutFieldOrder() {
//        return Collections.singletonList("datetime");
        //这里的参数要与配置文件的参数顺序保持一致
        return Arrays.asList("minAge", "maxAge");
    }

    //断言逻辑
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return exchange -> {
            ServerHttpRequest request = exchange.getRequest();
            Integer age = Integer.valueOf(request.getQueryParams().getFirst("age"));
            if (age > config.getMinAge() && age < config.getMaxAge()) {
                return true;
            }
            return false;
        };
    }

    //提供无参构造和getter setter
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;//18
        private int maxAge;//60
    }
}
