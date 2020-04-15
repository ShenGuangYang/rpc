package com.study.demo;

import com.study.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler{

    private String host;
    private int port;

    public Handler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 把一个执行类的信息封装成一个请求
        // 通过socket通信，从服务端获得具体的处理结果
        RpcRequest request = new RpcRequest();
        request.setParameters(args);
        request.setMethodName(method.getName());
        request.setClassName(method.getDeclaringClass().getName());
        Transport transport = new Transport(host, port);
        return transport.send(request);
    }
}
