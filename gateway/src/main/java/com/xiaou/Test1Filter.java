package com.xiaou;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class Test1Filter implements GlobalFilter, Ordered {
    private final static Logger Log = LoggerFactory.getLogger(Test1Filter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Log.info("Test1Fileter");
        return chain.filter(exchange);//继续走下一个过滤器
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
