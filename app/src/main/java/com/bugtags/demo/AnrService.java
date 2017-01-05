package com.bugtags.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by kevin on 28/11/2016.
 */

public class AnrService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("hello service anr");
        boolean a = true;
        while (a) {

        }

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
