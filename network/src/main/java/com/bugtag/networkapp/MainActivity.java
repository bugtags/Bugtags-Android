package com.bugtag.networkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void httpclient(View view) {
        startActivity(new Intent(this, HttpClientActivity.class));
    }

    public void urlconnection(View view) {
        startActivity(new Intent(this, UrlActivity.class));
    }

    public void okhttp2(View v) {
        startActivity(new Intent(this, Okhttp2Activity.class));
    }

    public void okhttp3(View v) {
        startActivity(new Intent(this, Okhttp3Activity.class));
    }

    public void volley(View v) {
        startActivity(new Intent(this, VolleyActivity.class));
    }
    public void loopj(View v) {
        startActivity(new Intent(this, LoopjActivity.class));
    }
    public void retrofit2(View v) {
        startActivity(new Intent(this, Retrofit2Activity.class));
    }
    public void retrofit1x(View v) {
        startActivity(new Intent(this, Retrofit1xActivity.class));
    }
}
