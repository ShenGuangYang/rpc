package com.study.demo.spring;

import com.study.IHelloService;

@RpcService(value = IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    public String sayHello(String context) {
        System.out.println("HelloServiceImpl sayHello:" + context);
        return "sayHello:" + context;
    }
}
