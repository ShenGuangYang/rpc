package com.study.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RpcServer implements InitializingBean, ApplicationContextAware {

    private int port;
    ExecutorService executorService = Executors.newCachedThreadPool();
    private Map<String, Object> handlerMap = new HashMap<>();


    public RpcServer(int port) {
        this.port = port;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // bio 阻塞
                executorService.execute(new MyProcessorHandler(socket, handlerMap)); // 线程池，可以异步处理请求
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                //拿到注解
                RpcService rpcService = serviceBean.getClass().getAnnotation((RpcService.class));
                String serviceName = rpcService.value().getName();
                handlerMap.put(serviceName, serviceBean);
            }
        }
    }
}
