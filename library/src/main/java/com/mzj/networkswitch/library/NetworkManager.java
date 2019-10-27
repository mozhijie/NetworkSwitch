package com.mzj.networkswitch.library;

import android.app.Application;
import android.content.IntentFilter;

import com.mzj.networkswitch.library.utils.Constants;

/**
 * 网络管理类
 */
public class NetworkManager {

    private static volatile NetworkManager instance;
    private Application application;

    private NetworkStateReceiver networkStateReceiver;

    private NetworkManager() {
        networkStateReceiver = new NetworkStateReceiver();
    }

    public static NetworkManager getDefault() {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("NetworkManager的Application为空");
        }
        return application;
    }

    public void init(Application application) {
        this.application = application;
        // 动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(networkStateReceiver, intentFilter);
    }

    public void register(Object object) {
        networkStateReceiver.register(object);
    }

}
