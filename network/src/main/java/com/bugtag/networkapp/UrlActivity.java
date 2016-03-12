package com.bugtag.networkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bugtag.networkapp.R;

public class UrlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
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
    }

    public void urlconnectionGetHtml(View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                HttpURLConnectionTest.getHtml();
            }
        });
    }

    public void urlconnectionGetJson(View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                HttpURLConnectionTest.getJson();
            }
        });

    }

    public void urlconnectionGetFile(final View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                HttpURLConnectionTest.getFile(view);
            }
        });

    }

    public void urlconnectionPostUrlencode(View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                HttpURLConnectionTest.postUrlencode();
            }
        });

    }

    public void urlconnectionPostMultipart(View view) {

        Async.run(new Runnable() {
            @Override
            public void run() {
                HttpURLConnectionTest.postMultipart(UrlActivity.this);
            }
        });

    }

}
