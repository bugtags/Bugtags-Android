package com.bugtags.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

public class StandAloneProcessService extends Service {
    Handler handler;

    public StandAloneProcessService() {
        handler = new Handler(Looper.myLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int type = intent.getIntExtra("type", 0);

        if (type == 1) {
            System.out.println("start");
        } else if (type == 2) {

            System.out.println("to crash");
            throw new RuntimeException("this is crash");
        }

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
