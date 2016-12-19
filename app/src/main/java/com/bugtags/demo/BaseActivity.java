package com.bugtags.demo;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;

public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Bugtags.onDispatchKeyEvent(this, event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, ev);
        return super.dispatchTouchEvent(ev);
    }
}