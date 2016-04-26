package com.bugtag.networkapp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by bugtags.com on 16/3/8.
 */
public class VolleyTest {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "VolleyTest";
    static Handler handler = new Handler(Looper.getMainLooper());
    static RequestQueue mRequestQueue;

    public static void init(Activity activity) {
        mRequestQueue = Volley.newRequestQueue(activity);
    }

    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";

        urlStr = "http://baidu.com";

        StringRequest request = new StringRequest(Request.Method.GET, urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error + "");
            }
        });

        mRequestQueue.add(request);
    }

    public static void getJson() {
        String urlStr = HOST_PREFIX + "/get/json?xxx=bb";

    }

    public static void getFile(final View view) {
        String urlStr = HOST_PREFIX + "/get/file?xxx=bb";

    }


    public static void postUrlencode() {
        String urlStr = HOST_PREFIX + "/post/encode?xxx=bb";

    }

    public static void postMultipart(Activity activity) {
        String urlStr = HOST_PREFIX + "/post/multer";

    }
}
