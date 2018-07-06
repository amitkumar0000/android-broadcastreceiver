package com.android.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.receiver.broadcastreceiver.MyBroadCastReceiver;
import com.android.receiver.broadcastreceiver.MyLocalBroadCastReceiver;

public class MainActivity extends AppCompatActivity {
    MyBroadCastReceiver myBroadCastReceiver;
    MyLocalBroadCastReceiver myLocalBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBroadCastReceiver = new MyBroadCastReceiver();
        myLocalBroadCastReceiver = new MyLocalBroadCastReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadCast();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBroadCast();
    }

    /*
    From Android Oreo, Registering BroadCast Receiver in Manifest will not work
     */
    private void registerBroadCast() {

        registerReceiver(myBroadCastReceiver,new IntentFilter("android.name.receiver"));
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadCastReceiver,
                new IntentFilter("android.name.localreceiver"));
    }

    private void unregisterBroadCast(){
        try{
            unregisterReceiver(myBroadCastReceiver);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadCastReceiver);
        }catch (Exception e){

        }

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:{
                sendBroadcastReceiver();
                break;
            }
            case R.id.button1:{
                sendLocalBroadcastReceiver();
                break;
            }
        }
    }

    private void sendLocalBroadcastReceiver() {
        Intent intent = new Intent();
        intent.setAction("android.name.localreceiver");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    private void sendBroadcastReceiver() {
        Intent intent = new Intent();
        intent.setAction("android.name.receiver");
        sendBroadcast(intent,"com.android.receiver.permission");
    }
}
