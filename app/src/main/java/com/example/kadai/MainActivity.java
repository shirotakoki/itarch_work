package com.example.kadai;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android-aidl-example";
    AidlSampleInterface mService = null;
    //ServiceConnectionを定義
    private ServiceConnection mServiceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mService = null;
                }

                @Override
                public void onServiceConnected(
                        ComponentName name, IBinder service) {
                    mService = AidlSampleInterface.Stub.asInterface(service);
                }
            };
    //インテントを立ち上げた場合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Intent sv = new Intent(AidlSampleInterface.class.getName());
        sv.setPackage("com.example.kadai");
        bindService(sv, //【5】
                mServiceConnection, BIND_AUTO_CREATE);

        button.setOnClickListener(this::onClick);
    }
    //クリックした場合
    private void onClick(View v) {
        String toastMessage = null;
        try {
            toastMessage = mService.result_food() + mService.result_drink();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        toast.show();
    }

}