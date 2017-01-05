package com.bugtags.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by bugtags.com on 15/7/29.
 */
public class FunctionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_functions_info);

        startService(new Intent(this,StandAloneProcessService.class));
    }

    public void anrClick(View view) {
        System.out.println("hello click anr");
        while (true){

        }
    }

    public void receiverClick(View view) {
        Intent intent = new Intent();
        intent.setAction("com.bugtags.anr.broadcast");
        sendBroadcast(intent);
    }

    public void serviceClick(View view) {
        Intent intent = new Intent(this, AnrService.class);
        startService(intent);
    }

    public void crashInProcess(View view) {
        Intent intent = new Intent(this, StandAloneProcessService.class);
        intent.putExtra("type", 2);

        startService(intent);
    }
}
