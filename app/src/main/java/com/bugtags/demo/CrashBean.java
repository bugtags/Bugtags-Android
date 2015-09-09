package com.bugtags.demo;

/**
 * Created by bugtags.com on 15/8/7.
 */
public class CrashBean {

    public CrashBean() {
    }

    public void different() {
        throw new RuntimeException("this is a demo crash current time：" + System.currentTimeMillis());
    }
}
