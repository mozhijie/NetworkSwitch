package com.mzj.networkswitch.library.listener;

import com.mzj.networkswitch.library.type.NetworkType;

/**
 * 网络监听接口
 */
public interface NetworkChangeObserver {
    void onConnect(NetworkType networkType);

    void onDisConnect();
}
