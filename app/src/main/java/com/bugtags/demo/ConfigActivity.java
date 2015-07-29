package com.bugtags.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bugtags.library.BugTags;
import com.bugtags.wrapper.BugtagsAppCompatActivity;

/**
 * Created by bugtags.com on 15/7/28.
 */
public class ConfigActivity extends BugtagsAppCompatActivity {

    private boolean mTrackUerStep = true;
    private boolean mTrackConsoleLog = true;
    private boolean mTrackCrashLog = true;

    private EditText mKeyText;
    private EditText mValueText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.main_config);
        }

        setContentView(R.layout.activity_config);

        mKeyText = (EditText) findViewById(R.id.keyText);
        mValueText = (EditText) findViewById(R.id.valueText);
    }

    public void onUserStep(View view) {
        mTrackUerStep = !mTrackUerStep;
        Button button = (Button) view;

        BugTags.setTrackingUserSteps(mTrackUerStep);

        button.setText(mTrackUerStep ? R.string.global_yes : R.string.global_no);
        Toast.makeText(this, R.string.config_set_user_data_succeed, Toast.LENGTH_SHORT).show();

        BugTags.log("onUserStep:" + mTrackUerStep);
    }

    public void onConsoleLog(View view) {
        mTrackConsoleLog = !mTrackConsoleLog;
        Button button = (Button) view;

        BugTags.setTrackingConsoleLog(mTrackConsoleLog);

        button.setText(mTrackConsoleLog ? R.string.global_yes : R.string.global_no);
        Toast.makeText(this, R.string.config_set_user_data_succeed, Toast.LENGTH_SHORT).show();

        BugTags.log("onConsoleLog:" + mTrackConsoleLog);
    }

    public void onCrashLog(View view) {
        mTrackCrashLog = !mTrackCrashLog;
        Button button = (Button) view;

        BugTags.setTrackingCrashes(mTrackCrashLog);

        button.setText(mTrackCrashLog ? R.string.global_yes : R.string.global_no);
        Toast.makeText(this, R.string.config_set_user_data_succeed, Toast.LENGTH_SHORT).show();

        BugTags.log("onCrashLog:" + mTrackCrashLog);
    }

    public void onCrash(View view) {
        Toast.makeText(this, mTrackCrashLog ? R.string.config_crash_collect : R.string.config_crash_not_collect, Toast.LENGTH_SHORT).show();

        BugTags.log("onCrash:");

        throw new RuntimeException("sample exception from bugtags");
    }

    public void onSetUseData(View view) {
        String key = mKeyText.getText().toString();
        String value = mValueText.getText().toString();

        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            Toast.makeText(this, R.string.config_user_data_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        BugTags.setUserData(key, value);

        BugTags.log("onSetUseData:" + " key:" + key + " value:" + value);

        Toast.makeText(this, R.string.config_set_user_data_succeed, Toast.LENGTH_SHORT).show();
    }

    public void onPost(View view) {
        BugTags.sendFeedback("sample feedback");

        BugTags.log("onPost:");

        Toast.makeText(this, R.string.feedback_send_succeed, Toast.LENGTH_SHORT).show();
    }
}
