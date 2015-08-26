package com.bugtags.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bugtags.library.Bugtags;

/**
 * Created by bugtags.com on 15/7/29.
 */
public class FeedbackActivity extends BaseActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_feedback);

        mEditText = (EditText) findViewById(R.id.inputText);
    }

    public void onPost(View view) {
        String message = mEditText.getText().toString();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, R.string.feedback_text_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        Bugtags.sendFeedback(message);
        Toast.makeText(this, R.string.feedback_send_succeed, Toast.LENGTH_SHORT).show();

        finish();
    }
}
