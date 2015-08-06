package com.bugtags.demo;

import com.bugtags.library.Bugtags;

/**
 * Created by bugtags.com on 15/7/24.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bugtags.start("d3ad445a39bf60628f7acd4bd08eff4f", this, Bugtags.BTGInvocationEventBubble);
    }
}
