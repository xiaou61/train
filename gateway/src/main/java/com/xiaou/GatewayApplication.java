package com.xiaou;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class GatewayApplication {
    //调试日志
    private final static Logger Log = LoggerFactory.getLogger(GatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication app =new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        Log.info("启动成功！");
        Log.info("网关地址:\t http://127.0.0.1:{}",env.getProperty("server.port"));
    }

}