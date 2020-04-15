package com.study.demo;

import com.study.IHelloService;

public class HelloService2Impl implements IHelloService {
    public String sayHello(String context) {
        System.out.println("HelloService2Impl sayHello:" + context);
        return "sayHello:" + context;
    }
}
