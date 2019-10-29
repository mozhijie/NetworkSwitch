package com.mzj.networkswitch.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.mzj.networkswitch.library.listener.NetworkChangeObserver;
import com.mzj.networkswitch.library.type.NetworkType;
import com.mzj.networkswitch.library.utils.Constants;
import com.mzj.networkswitch.library.utils.NetworkUtils;

public class NetworkStateReceiver2 extends BroadcastReceiver {

    private NetworkType networkType;
    private NetworkChangeObserver networkChangeObserver;

    public NetworkStateReceiver2() {
        this.networkType = NetworkType.NONE;
    }

    public void setNetworkChangeObserver(NetworkChangeObserver networkChangeObserver) {
        this.networkChangeObserver = networkChangeObserver;
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
            ConnectivityManager connectivityManager = (ConnectivityManager) NetworkManager2.getDefault().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            networkType = NetworkUtils.getNetworkType(connectivityManager);
            if (NetworkUtils.isNetworkAvailable(connectivityManager)) {
                Log.e(Constants.LOG_TAG, "网络连接成功");
                if (networkChangeObserver != null) {
                    networkChangeObserver.onConnect(networkType);
                }
            } else {
                Log.e(Constants.LOG_TAG, "网络连接失败");
                if (networkChangeObserver != null) {
                    networkChangeObserver.onDisConnect();
                }
            }
        }
    }

}
