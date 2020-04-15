package com.study.demo;

import com.study.IHelloService;

public class Server {

    public static void main(String[] args) {

        IHelloService helloService = new HelloService2Impl();
        ServerProxy serverProxy = new ServerProxy();
        serverProxy.publisher(helloService, 8080 );
    }
}
