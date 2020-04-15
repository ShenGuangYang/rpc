package com.study.demo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerProxy {

    ExecutorService executorService = Executors.newCachedThreadPool();
    public void publisher(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // bio 阻塞
                executorService.execute(new MyProcessorHandler(socket, service)); // 线程池，可以异步处理请求
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
