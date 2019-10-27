package com.mzj.networkswitch;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mzj.networkswitch.library.NetworkManager;
import com.mzj.networkswitch.library.annotation.Network;
import com.mzj.networkswitch.library.type.NetworkType;

public class FirstActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        textView = findViewById(R.id.textView);

        NetworkManager.getDefault().register(this);
    }

    @Network(networkType = NetworkType.AUTO)
    public void network(NetworkType networkType) {
        switch (networkType) {
            case WIFI:
                Log.e("FirstActivity", "WIFI");
                break;
            case CMNET:
            case CMWAP:
                Log.e("FirstActivity", networkType.name());
                break;
            case NONE:
                Log.e("FirstActivity", "NONE");
                break;
        }
        textView.setText(networkType.name());
    }

}
