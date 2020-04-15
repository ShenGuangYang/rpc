package com.study.demo;

import com.study.IHelloService;

import java.lang.reflect.Proxy;

public class ClientProxy {

    // 通过动态代理去代理
    // 可以在代理类里去跟服务端通信
    // 然后获得返回结果
    public <T> T clientProxy(Class<IHelloService> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls},
                new Handler(host, port));
    }

}
