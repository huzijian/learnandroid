package com.example.zijianhu.myservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button btnstartService;
    private Button btnstopService;
    private Button btnbindService;
    private Button btnunbindService;
    private MyService.MyBinder myBinder;

    boolean mBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
            myBinder.getProgress();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstartService = (Button) findViewById(R.id.startService);
        btnstopService = (Button) findViewById(R.id.stopService);
        btnbindService = (Button) findViewById(R.id.bindService);
        btnunbindService = (Button) findViewById(R.id.unbindService);

        btnstartService.setOnClickListener(this);
        btnstopService.setOnClickListener(this);
        btnbindService.setOnClickListener(this);
        btnunbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stopService:
                Intent stoptIntent = new Intent(this, MyService.class);
                stopService(stoptIntent);
                break;
            case R.id.bindService:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                if(mBound) {
                    unbindService(connection);
                    mBound = false;
                }
                break;
        }
    }
}


















