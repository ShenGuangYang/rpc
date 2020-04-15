package com.study.demo.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.study.demo")
public class SpringConfig {

    @Bean(name = "rpcServer")
    public RpcServer rpcServer() {
        return new RpcServer(8080);
    }


}
