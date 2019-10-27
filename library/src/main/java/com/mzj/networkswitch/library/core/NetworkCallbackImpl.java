package com.mzj.networkswitch.library.core;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.mzj.networkswitch.library.utils.Constants;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        Log.e(Constants.LOG_TAG, "网络连接成功");
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        Log.e(Constants.LOG_TAG, "网络连接失败");
    }

    @Override
    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.e(Constants.LOG_TAG, "网络发生变化，类型为WIFI");
            } else {
                Log.e(Constants.LOG_TAG, "网络发生变化，类型为其他");
            }
        }
    }

}
