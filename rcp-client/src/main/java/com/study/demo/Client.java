package com.study.demo;

import com.study.IHelloService;

public class Client {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy();
        IHelloService helloService = clientProxy.clientProxy(IHelloService.class, "localhost", 8080);
        helloService.sayHello("shenshen");
    }
}
