package com.mzj.networkswitch.library;

import android.app.Application;
import android.content.IntentFilter;

import com.mzj.networkswitch.library.listener.NetworkChangeObserver;
import com.mzj.networkswitch.library.utils.Constants;

/**
 * 网络管理类
 */
public class NetworkManager2 {

    private static volatile NetworkManager2 instance;
    private Application application;

    private NetworkStateReceiver2 networkStateReceiver2;

    private NetworkManager2() {
        networkStateReceiver2 = new NetworkStateReceiver2();
    }

    public static NetworkManager2 getDefault() {
        if (instance == null) {
            synchronized (NetworkManager2.class) {
                if (instance == null) {
                    instance = new NetworkManager2();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("NetworkManager2的Application为空");
        }
        return application;
    }

    public void init(Application application) {
        this.application = application;
        // 动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(networkStateReceiver2, intentFilter);
    }

    public void setNetworkChangeObserver(NetworkChangeObserver networkChangeObserver) {
        this.networkStateReceiver2.setNetworkChangeObserver(networkChangeObserver);
    }

}
