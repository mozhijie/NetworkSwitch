package com.mzj.networkswitch;

import android.app.Application;

import com.mzj.networkswitch.library.NetworkManager;
import com.mzj.networkswitch.library.NetworkManager2;
import com.mzj.networkswitch.library.NetworkManager3;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化NetworkManager
        // ------ 广播方式------
        // 广播方式一
        NetworkManager.getDefault().init(this);
        // 广播方式二
//        NetworkManager2.getDefault().init(this);
        // ------ 广播方式 end------
        /**
         * 运行该示例
         * 需取消注释 App-NetworkManager3 和 AndroidManifest-NetworkStateReceiver
         * 注释 App-NetworkManager、NetworkManager2 和 AndroidManifest-NetworkStateReceiver2
         */
//        NetworkManager3.getDefault().init(this);
    }

}
