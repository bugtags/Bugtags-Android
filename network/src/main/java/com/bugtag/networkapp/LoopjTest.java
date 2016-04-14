package com.bugtag.networkapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bugtags.com on 16/3/8.
 */
public class LoopjTest {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "LoopjTest";
    static Handler handler = new Handler(Looper.getMainLooper());

    static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();


    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";

        mAsyncHttpClient.addHeader("a", "b");
        mAsyncHttpClient.addHeader("b", "c");

        mAsyncHttpClient.get(urlStr, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static void getJson() {
        String urlStr = HOST_PREFIX + "/get/json?xxx=bb";

        mAsyncHttpClient.get(urlStr, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static void getFile(final View view) {
        String urlStr = HOST_PREFIX + "/get/file?xxx=bb";
        mAsyncHttpClient.get(urlStr, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                final Bitmap bitmap = BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);

                if (bitmap != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((Button) view).setBackgroundDrawable(new BitmapDrawable(bitmap));
                        }
                    });
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public static void postUrlencode() {
        String urlStr = HOST_PREFIX + "/post/encode?xxx=bb";

        RequestParams requestParams = new RequestParams();
        requestParams.put("name", "hello");
        requestParams.put("p1", 1);
        requestParams.put("p2", 2);
        mAsyncHttpClient.post(urlStr, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static void postMultipart(Activity activity) {
        String urlStr = HOST_PREFIX + "/post/multer";

        RequestParams requestParams = new RequestParams();
        requestParams.put("name", "hello");
        requestParams.put("p1", 1);
        requestParams.put("p2", 2);

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";

        requestParams.put(attachmentName, new ByteArrayInputStream(stream.toByteArray()), attachmentFileName, "image/png");

        mAsyncHttpClient.post(urlStr, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
