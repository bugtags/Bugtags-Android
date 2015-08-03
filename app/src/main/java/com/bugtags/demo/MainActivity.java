package com.bugtags.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bugtags.wrapper.BugtagsAppCompatActivity;

public class MainActivity extends BugtagsAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFeedback(View view) {
        startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
    }

    public void onCustom(View view) {
        startActivity(new Intent(MainActivity.this, CustomActivity.class));
    }

    public void onFunctions(View view) {
        startActivity(new Intent(MainActivity.this, FunctionsActivity.class));
    }

    public void onConfig(View view) {
        startActivity(new Intent(MainActivity.this, ConfigActivity.class));
    }
}
