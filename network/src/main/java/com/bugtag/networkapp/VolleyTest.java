package com.bugtag.networkapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bugtag.networkapp.volley.GzipStringRequest;
import com.bugtag.networkapp.volley.MultipartRequest;
import com.bugtag.networkapp.volley.MultipartRequestParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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
        VolleyLog.DEBUG = true;
    }

    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";

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

        request.setShouldCache(false);
        mRequestQueue.add(request);
    }

    public static void getJson() {
        String urlStr = HOST_PREFIX + "/get/json?xxx=bb";

        GzipStringRequest request = new GzipStringRequest(urlStr, new Response.Listener<String>() {
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

        request.setUserAgent("bugtags");
        request.setShouldCache(false);
        mRequestQueue.add(request);
    }

    public static void getFile(final View view) {
        String urlStr = HOST_PREFIX + "/get/file?xxx=bb";

        ImageRequest imgRequest = new ImageRequest(urlStr, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                if (response != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button) view).setBackgroundDrawable(new BitmapDrawable(response));
                        }
                    });
                }
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(imgRequest);
    }

    public static void postUrlencode() {
        String urlStr = HOST_PREFIX + "/post/encode?xxx=bb";

        MultipartRequestParams requestParams = new MultipartRequestParams();

        requestParams.put("name", "hello");
        requestParams.put("p1", "1");
        requestParams.put("p2", "2");

        mRequestQueue.add(new MultipartRequest(Request.Method.POST, requestParams, urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        }));
    }

    public static void postMultipart(Activity activity) {
        String urlStr = HOST_PREFIX + "/post/multer";


        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        MultipartRequestParams requestParams = new MultipartRequestParams();

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";

        requestParams.put(attachmentName, new ByteArrayInputStream(stream.toByteArray()), attachmentFileName, "image/png");

        mRequestQueue.add(new MultipartRequest(Request.Method.POST, requestParams, urlStr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        }));
    }
}
