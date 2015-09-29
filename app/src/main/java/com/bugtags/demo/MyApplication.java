package com.bugtags.demo;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;

/**
 * Created by bugtags.com on 15/7/24.
 */
public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //customizable init option
        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).
                build();
        Bugtags.start("d3ad445a39bf60628f7acd4bd08eff4f", this, Bugtags.BTGInvocationEventBubble,options);
    }
}
