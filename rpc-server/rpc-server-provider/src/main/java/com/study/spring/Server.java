package com.study.demo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Server {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.start();
    }
}
