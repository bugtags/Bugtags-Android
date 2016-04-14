package com.bugtag.networkapp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.bugtag.networkapp.retrofit1x.Api;


/**
 * Created by bugtags.com on 16/4/8.
 */
public class Retrofit1xTest {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "Okhttp3Test";
    static Handler handler = new Handler(Looper.getMainLooper());

    static RestAdapter restAdapter = new RestAdapter.Builder().
            setLogLevel(RestAdapter.LogLevel.FULL).
            setEndpoint(HOST_PREFIX).
            build();

    static Api api = restAdapter.create(Api.class);

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
