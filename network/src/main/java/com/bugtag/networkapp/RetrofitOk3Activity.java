package com.bugtag.networkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RetrofitOk3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_ok3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RetrofitOk3Test.init(this);
    }

    public void getHtml(View view) {
        RetrofitOk3Test.getHtml();
    }

    public void getJson(View view) {
        RetrofitOk3Test.getJson();
    }

    public void getFile(final View view) {
        RetrofitOk3Test.getFile(view);
    }

    public void postUrlencode(View view) {
        RetrofitOk3Test.postUrlencode();
    }

    public void postMultipart(View view) {
        RetrofitOk3Test.postMultipart(RetrofitOk3Activity.this);
    }

}
