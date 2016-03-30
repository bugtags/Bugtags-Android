package com.bugtag.networkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoopjActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loopj);
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


    public void getHtml(View view) {
        LoopjTest.getHtml();
    }

    public void getJson(View view) {
        LoopjTest.getJson();
    }

    public void getFile(final View view) {
        LoopjTest.getFile(view);
    }

    public void postUrlencode(View view) {
        LoopjTest.postUrlencode();
    }

    public void postMultipart(View view) {
        LoopjTest.postMultipart(LoopjActivity.this);
    }


}
