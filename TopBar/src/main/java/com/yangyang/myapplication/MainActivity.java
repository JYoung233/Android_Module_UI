package com.yangyang.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView im;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TopBar topbar= (TopBar) findViewById(R.id.topbar);
        im= (ImageView) findViewById(R.id.imageView);
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
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0x123){
                   im.setImageResource(R.drawable.a);

                }
            }
        };
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Message msg=handler.obtainMessage();
                msg.what=0x123;
                handler.sendMessage(msg);
                System.out.println("Hello world");
            }
        };
        Timer time=new Timer();
        time.schedule(task,1000);//后面的时间不会循环发送消息，只是说间隔1秒后发送消息。
        //handler.postDelayed(new MyThread(),1000);

    }
    class MyThread implements Runnable{

        @Override
        public void run() {
            Message msg=handler.obtainMessage();
            msg.what=0x123;//这里的信息号是随便填的吗
            try {
                Thread.sleep(1000);
                //msg.sendToTarget();
                System.out.println("This is the main handler");
                 handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}


