package com.bugtag.networkapp;

import android.app.Application;

import com.bugtags.library.Bugtags;

/**
 * Created by bugtags.com on 16/3/11.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Bugtags.start("8f443d183b1d202a5b006faa4718e1a6", this, Bugtags.BTGInvocationEventBubble);
    }
}
