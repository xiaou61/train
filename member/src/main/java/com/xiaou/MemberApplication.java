package com.xiaou;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.xiaou.mapper")
public class MemberApplication {
    //调试日志
    private final static Logger Log = LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {
        SpringApplication app =new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        Log.info("启动成功！");
        Log.info("测试地址:\t http://127.0.0.1:{}{}/hello",env.getProperty("server.port"),env.getProperty("server.servlet.context-path"));
    }

}