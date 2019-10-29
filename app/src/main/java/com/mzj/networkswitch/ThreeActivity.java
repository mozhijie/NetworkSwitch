package com.mzj.networkswitch;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mzj.networkswitch.library.NetworkManager3;
import com.mzj.networkswitch.library.annotation.Network;
import com.mzj.networkswitch.library.type.NetworkType;

public class ThreeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        textView = findViewById(R.id.textView);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkManager3.getDefault().register(this);
        }
    }

    @Network(networkType = NetworkType.AUTO)
    public void network(NetworkType networkType) {
        switch (networkType) {
            case WIFI:
                Log.e("ThreeActivity", "WIFI");
                break;
            case CMNET:
            case CMWAP:
                Log.e("ThreeActivity", networkType.name());
                break;
            case NONE:
                Log.e("ThreeActivity", "NONE");
                break;
        }
        textView.setText(networkType.name());
    }

}
