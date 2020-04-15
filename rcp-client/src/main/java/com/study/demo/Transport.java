package com.study.demo;

import com.study.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Transport {

    private String host;
    private int port;

    public Transport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest request) {
        Object result= null;
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            // 把request 请求信息序列化
            oos.writeObject(request);
            oos.flush();
            // 获得返回结果
            result=ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
