package com.bugtags.demo;

import com.bugtags.library.Bugtags;

/**
 * Created by bugtags.com on 15/7/24.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bugtags.start("f9465c17e83c070e9c27934f463b4bde", this, Bugtags.BTGInvocationEventBubble);
    }
}
