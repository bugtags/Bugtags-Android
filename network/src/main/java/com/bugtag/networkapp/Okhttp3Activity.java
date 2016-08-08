package com.bugtag.networkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Okhttp3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp3);
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

        Okhttp3Test.init(this);
    }

    public void getHtml(View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                Okhttp3Test.getHtml();
            }
        });
    }

    public void getJson(View view) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                Okhttp3Test.getJson();
            }
        });
    }

    public void getFile(final View view) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                Okhttp3Test.getFile(view);
            }
        });
    }

    public void postUrlencode(View view) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                Okhttp3Test.postUrlencode();
            }
        });
    }

    public void postMultipart(View view) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                Okhttp3Test.postMultipart(Okhttp3Activity.this);
            }
        });
    }

}
