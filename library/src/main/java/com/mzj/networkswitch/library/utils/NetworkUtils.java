package com.mzj.networkswitch.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mzj.networkswitch.library.NetworkManager;
import com.mzj.networkswitch.library.type.NetworkType;

public class NetworkUtils {

    /**
     * 网络是否可用
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvailable(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return false;
        }
        // 获取所有网络信息
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo.length > 0) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     */
    @SuppressLint("MissingPermission")
    public static NetworkType getNetworkType(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return NetworkType.NONE;
        }
        // 获取活动网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NetworkType.NONE;
        }
        int type = activeNetworkInfo.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            if (activeNetworkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetworkType.CMNET;
            } else {
                return NetworkType.CMWAP;
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return NetworkType.WIFI;
        }
        return NetworkType.NONE;
    }

}
