package com.bugtag.networkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class VolleyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
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

    @Override
    protected void onStart() {
        super.onStart();

        VolleyTest.init(this);
    }

    public void getHtml(View view) {
        VolleyTest.getHtml();
    }

    public void getJson(View view) {
        VolleyTest.getJson();
    }

    public void getFile(final View view) {
        VolleyTest.getFile(view);
    }

    public void postUrlencode(View view) {
        VolleyTest.postUrlencode();
    }

    public void postMultipart(View view) {
        VolleyTest.postMultipart(VolleyActivity.this);
    }

}
