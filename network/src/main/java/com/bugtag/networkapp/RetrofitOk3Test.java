package com.bugtag.networkapp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.bugtag.networkapp.retrofit1x.Api;
import com.jakewharton.retrofit.Ok3Client;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by bugtags.com on 16/4/8.
 */
public class RetrofitOk3Test {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "Okhttp3Test";
    static Handler handler = new Handler(Looper.getMainLooper());

    public static void init(Activity activity) {
        File cacheFile = new File(activity.getApplicationContext().getCacheDir().getAbsolutePath(), "HttpCache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.cache(cache);
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        OkHttpClient client = okBuilder.build();

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(new Ok3Client(client));

        restAdapter = builder.
                setLogLevel(RestAdapter.LogLevel.FULL).
                setEndpoint(HOST_PREFIX).
                build();

        api = restAdapter.create(Api.class);
    }

    static RestAdapter restAdapter;

    static Api api;

    public static void getHtml() {
        api.getHtml(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "getHtml" + s);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static void getJson() {

        api.getJson(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "getJson" + s);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static void getFile(final View view) {

    }


    public static void postUrlencode() {


    }

    public static void postMultipart(Activity activity) {

    }
}
