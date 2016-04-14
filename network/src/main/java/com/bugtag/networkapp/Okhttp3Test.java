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

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by bugtags.com on 16/3/8.
 */
public class Okhttp3Test {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "Okhttp3Test";
    static Handler handler = new Handler(Looper.getMainLooper());

    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";

        Request request = new Request.Builder().
                url(urlStr).
                addHeader("a", "b").
                addHeader("b", "c").
                build();
        Log.d(TAG, urlStr);
        try {
            Response response = okHttpClient.newCall(request).execute();

            Log.d(TAG, response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getJson() {
        String urlStr = HOST_PREFIX + "/get/json?xxx=bb";
        Request request = new Request.Builder().
                addHeader("a", "b").
                addHeader("b", "c").
                url(urlStr).
                build();


        Log.d(TAG, urlStr);
        try {
            Response response = okHttpClient.newCall(request).execute();

            Log.d(TAG, response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFile(final View view) {
        String urlStr = HOST_PREFIX + "/get/file?xxx=bb";
        Request request = new Request.Builder().
                url(urlStr).
                addHeader("a", "b").
                addHeader("b", "c").
                build();
        Log.d(TAG, urlStr);
        try {
            Response response = okHttpClient.newCall(request).execute();

            final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

            if (bitmap != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((Button) view).setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void postUrlencode() {
        String urlStr = HOST_PREFIX + "/post/encode?xxx=bb";

        RequestBody body = new FormBody.Builder().
                add("name", "hello").
                add("p1", "1").
                add("p2", "2").
                build();

        Request request = new Request.Builder().
                url(urlStr).post(body).
                addHeader("a", "b").
                addHeader("b", "c").
                build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.d(TAG, response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postMultipart(Activity activity) {
        String urlStr = HOST_PREFIX + "/post/multer";

        //bitmap

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";

        RequestBody body = new MultipartBody.Builder().
                setType(MultipartBody.FORM).
                addFormDataPart(attachmentName, attachmentFileName, RequestBody.create(MediaType.parse("image/png"), stream.toByteArray())).
                build();

        Request request = new Request.Builder().
                url(urlStr).post(body).
                addHeader("a", "b").
                addHeader("b", "c").
                build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.d(TAG, response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
