package com.mzj.networkswitch.library.bean;

import com.mzj.networkswitch.library.type.NetworkType;

import java.lang.reflect.Method;

/**
 * 保存符合要求的网络监听注解方法，封装类
 */
public class MethodManager {

    // 参数类型
    private Class<?> type;
    // 网络类型
    private NetworkType networkType;
    // 需要执行的方法
    private Method method;

    public MethodManager(Class<?> type, NetworkType networkType, Method method) {
        this.type = type;
        this.networkType = networkType;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
