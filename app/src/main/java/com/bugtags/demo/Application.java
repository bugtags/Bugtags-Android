package com.bugtags.demo;

import com.bugtags.library.BugTags;

/**
 * Created by bugtags.com on 15/7/24.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BugTags.start("59a5d4fb43a3f66d12bdf1827fb79c5b", this, BugTags.BTGInvocationEventBubble);
    }
}
