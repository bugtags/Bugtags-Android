package com.bugtags.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kevin on 28/11/2016.
 */

public class AnrReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("hello receiver anr");
        while (true){

        }
    }
}
