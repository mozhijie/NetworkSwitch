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
        // 运行该示例
        // 需取消注释 App-NetworkManager 和 AndroidManifest-NetworkStateReceiver
        // 注释 App-NetworkManager2、NetworkManager3 和 AndroidManifest-NetworkStateReceiver2、NetworkStateReceiver3
        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
            }
        });
        // 运行该示例
        // 需取消注释 App-NetworkManager2 和 AndroidManifest-NetworkStateReceiver2
        // 注释 App-NetworkManager、NetworkManager3 和 AndroidManifest-NetworkStateReceiver、NetworkStateReceiver3
        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        // 运行该示例
        // 需取消注释 App-NetworkManager3 和 AndroidManifest-NetworkStateReceiver3
        // 注释 App-NetworkManager、NetworkManager2 和 AndroidManifest-NetworkStateReceiver、NetworkStateReceiver2
        findViewById(R.id.btn_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
            }
        });
    }

}
