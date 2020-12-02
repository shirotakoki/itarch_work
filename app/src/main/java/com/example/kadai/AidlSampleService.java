package com.example.kadai;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Random;

public class AidlSampleService extends Service {

    private static final String TAG = "AidlSampleService";
    private Random rnd =new Random();
    private static String[] food_menu =
            {"冷ややっこ:ツナ缶:ゆでたまご",
                    "白米:唐揚げ:サラダ",
                    "白米:豚モモの焼き物:生卵",
                    "冷ややっこ:サンマの缶詰:ささみ",
                    "蒸し鶏むね肉:梅干し:サラダ",
                    "そば:ささみ",
                    "カレーライス:ツナ缶",
            };
    private static String[] drink_menu =
            {":プロテイン",
                    ":お茶",
                    ":プロテイン",
                    ":水",
                    ":プロテイン"
            };

    private AidlSampleInterface.Stub mStub =
            new AidlSampleInterface.Stub() {
                @Override
                public String result_food() throws RemoteException {
                    return food_menu[rnd.nextInt(7)];
                }
                public String result_drink() throws RemoteException {
                    return drink_menu[rnd.nextInt(5)];
                }
            };

    @Override
    public IBinder onBind(Intent intent) {
        android.util.Log.i(TAG, "onBind");
        return mStub;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.util.Log.i(TAG, "onDestroy");
    }
}