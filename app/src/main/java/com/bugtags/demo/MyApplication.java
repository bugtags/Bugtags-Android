package com.bugtags.demo;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsCallback;
import com.bugtags.library.BugtagsOptions;

import io.bugtags.insta.BugtagsInsta;

/**
 * Created by bugtags.com on 15/7/24.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //customizable init option
        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).//是否获取位置
                trackingCrashLog(true).//是否收集crash
                trackingConsoleLog(true).//是否收集console log
                trackingUserSteps(true).//是否收集用户操作步骤
                startAsync(false).
                startCallback(new BugtagsCallback() {
                    @Override
                    public void run() {

                    }
                }).
                enableUserSignIn(true).
                build();

        Bugtags.start(BuildConfig.DEBUG ? "d3ad445a39bf60628f7acd4bd08eff4f" : "186cc7c96a5966b6615b34217b293f4f", this, Bugtags.BTGInvocationEventBubble, options);

        Bugtags.setBeforeSendingCallback(new BugtagsCallback() {
            @Override
            public void run() {
                Bugtags.log("before");
            }
        });

        Bugtags.setAfterSendingCallback(new BugtagsCallback() {
            @Override
            public void run() {
                Bugtags.log("after");
            }
        });

        Bugtags.registerPlugin(new BugtagsInsta());
    }
}
