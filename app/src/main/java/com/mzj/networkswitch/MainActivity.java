package com.mzj.networkswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 运行该实例
         * 需取消注释App-NetworkManager和AndroidManifest-NetworkStateReceiver
         * 注释App-NetworkManager2、NetworkManager3和AndroidManifest-NetworkStateReceiver2
         */
        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
            }
        });
        /**
         * 运行该实例
         * 需取消注释App-NetworkManager2和AndroidManifest-NetworkStateReceiver2
         * 注释App-NetworkManager、NetworkManager3和AndroidManifest-NetworkStateReceiver
         */
        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

}
