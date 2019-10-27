package com.mzj.networkswitch.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mzj.networkswitch.library.annotation.Network;
import com.mzj.networkswitch.library.bean.MethodManager;
import com.mzj.networkswitch.library.type.NetworkType;
import com.mzj.networkswitch.library.utils.Constants;
import com.mzj.networkswitch.library.utils.NetworkUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetworkStateReceiver extends BroadcastReceiver {

    private NetworkType networkType;
    // key:Activity/Fragment value:对应key的所有订阅监听网络的方法集合
    private Map<Object, List<MethodManager>> networkList;

    public NetworkStateReceiver() {
        networkType = NetworkType.NONE;
        networkList = new HashMap<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.e(Constants.LOG_TAG, "网络异常");
            return;
        }
        // 处理广播的事件
        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)) {
            Log.e(Constants.LOG_TAG, "网络发生变化");
            networkType = NetworkUtils.getNetworkType();
            if (NetworkUtils.isNetworkAvailable()) {
                Log.e(Constants.LOG_TAG, "网络连接成功");
            } else {
                Log.e(Constants.LOG_TAG, "网络连接失败");
            }
            post(networkType);
        }
    }

    private void post(NetworkType networkType) {
        if (networkList.isEmpty()) {
            return;
        }
        Set<Object> objects = networkList.keySet();
        for (Object object : objects) {
            // 获取所有订阅方法
            List<MethodManager> methodManagers = networkList.get(object);
            if (methodManagers != null) {
                for (MethodManager methodManager : methodManagers) {
                    // 参数的匹配
                    if (methodManager.getType().isAssignableFrom(networkType.getClass())) {
                        switch (methodManager.getNetworkType()) {
                            case AUTO:
                                invoke(methodManager, object, networkType);
                                break;
                            case WIFI:
                                if (networkType == NetworkType.WIFI || networkType == NetworkType.NONE) {
                                    invoke(methodManager, object, networkType);
                                }
                                break;
                            case CMNET:
                            case CMWAP:
                                if (networkType == NetworkType.CMNET || networkType == NetworkType.CMWAP || networkType == NetworkType.NONE) {
                                    invoke(methodManager, object, networkType);
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    private void invoke(MethodManager methodManager, Object object, NetworkType networkType) {
        try {
            methodManager.getMethod().invoke(object, networkType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void register(Object object) {
        List<MethodManager> methodList = networkList.get(object);
        if (methodList == null) {
            // 收集
            methodList = findAnnotationMethod(object);
            networkList.put(object, methodList);
        }
    }

    private List<MethodManager> findAnnotationMethod(Object object) {
        List<MethodManager> methodList = new ArrayList<>();
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        // 订阅方法的收集
        for (Method method : methods) {
            Network network = method.getAnnotation(Network.class);
            if (network == null) {
                continue;
            }
//            // 获取方法的返回值，校验一
//            method.getGenericReturnType();
            // 获取方法的参数，校验二
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException(method.getName() + "方法的参数有且只有一个");
            }
            MethodManager methodManager = new MethodManager(parameterTypes[0], network.networkType(), method);
            methodList.add(methodManager);
        }
        return methodList;
    }

}
