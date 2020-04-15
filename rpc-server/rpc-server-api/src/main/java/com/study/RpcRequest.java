package com.study;

import java.io.Serializable;

// 封装服务端、客户端可以进行序列化、反序列化通信的请求
public class RpcRequest implements Serializable{

    private String className;
    private String methodName;
    private Object[] parameters;
    // 可以增加版本信息管理，本demo未实现
    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
