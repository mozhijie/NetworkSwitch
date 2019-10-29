package com.mzj.networkswitch.library;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import com.mzj.networkswitch.library.core.NetworkCallbackImpl;
import com.mzj.networkswitch.library.utils.Constants;

/**
 * 网络管理类
 */
public class NetworkManager3 {

    private static volatile NetworkManager3 instance;
    private Application application;

    private NetworkStateReceiver3 networkStateReceiver3;

    private NetworkManager3() {
        networkStateReceiver3 = new NetworkStateReceiver3();
    }

    public static NetworkManager3 getDefault() {
        if (instance == null) {
            synchronized (NetworkManager3.class) {
                if (instance == null) {
                    instance = new NetworkManager3();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("NetworkManager3的Application为空");
        }
        return application;
    }

    @SuppressLint("MissingPermission")
    public void init(Application application) {
        this.application = application;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager.NetworkCallback networkCallback = new NetworkCallbackImpl();
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            NetworkRequest networkRequest = builder.build();
            ConnectivityManager connectivityManager = (ConnectivityManager) NetworkManager3.getDefault().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
            }
        } else {
            // 动态注册广播
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
            application.registerReceiver(networkStateReceiver3, intentFilter);
        }
    }

    public void register(Object object) {
        networkStateReceiver3.register(object);
    }

}
