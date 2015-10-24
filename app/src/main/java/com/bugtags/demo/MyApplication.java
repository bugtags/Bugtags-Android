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
                trackingLocation(true).//是否获取位置
                trackingCrashLog(true).//是否收集crash
                trackingConsoleLog(true).//是否收集console log
                trackingUserSteps(true).//是否收集用户操作步骤
                crashWithScreenshot(true).//crash附带图
                build();
        Bugtags.start("d3ad445a39bf60628f7acd4bd08eff4f", this, Bugtags.BTGInvocationEventBubble, options);
    }
}
