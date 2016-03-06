package com.yangyang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopBar topbar= (TopBar) findViewById(R.id.topbar);
        topbar.setOnClikTopBarListenner(new TopBar.OnTopBarListenner() {
            @Override
            public void leftclick() {
                Toast.makeText(MainActivity.this,"点击了左键",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightclick() {
                Toast.makeText(MainActivity.this,"点击了右键",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
