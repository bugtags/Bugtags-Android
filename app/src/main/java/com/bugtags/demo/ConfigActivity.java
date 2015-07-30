package com.bugtags.demo;

import android.os.Bundle;
import com.bugtags.wrapper.BugtagsAppCompatActivity;

/**
 * Created by bugtags.com on 15/7/28.
 */
public class ConfigActivity extends BugtagsAppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.main_config);
        }

        setContentView(R.layout.activity_config_quick);

    }
}
