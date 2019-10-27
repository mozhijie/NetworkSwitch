package com.mzj.networkswitch;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mzj.networkswitch.library.NetworkManager2;
import com.mzj.networkswitch.library.listener.NetworkChangeObserver;
import com.mzj.networkswitch.library.type.NetworkType;

public class SecondActivity extends AppCompatActivity implements NetworkChangeObserver {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        textView = findViewById(R.id.textView);

        NetworkManager2.getDefault().setNetworkStateReceiver2(this);
    }

    @Override
    public void onConnect(NetworkType networkType) {
        Log.e("SecondActivity", "onConnect:" + networkType);
        textView.setText("onConnect:" + networkType);
    }

    @Override
    public void onDisConnect() {
        Log.e("SecondActivity", "onDisConnect");
        textView.setText("onDisConnect");
    }

}
