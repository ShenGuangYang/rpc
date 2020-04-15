package com.study.demo;

import com.study.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

// 通过线程来实现异步处理bio请求
public class MyProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public MyProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
            // 反序列化客户端请求信息
            RpcRequest request = (RpcRequest)ois.readObject();
            // 反射调用本地服务
            Object result = invoke(request);
            // 结果信息返回客户端
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //拿到客户端请求的参数
        Object[] args = request.getParameters();
        //获得每个参数的类型
        Class[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Class<?> clazz = Class.forName(request.getClassName());// 加载请求类
        Method method = clazz.getMethod(request.getMethodName(), types);// 找到对应的方法
        return method.invoke(service, args);// 反射调用
    }
}
