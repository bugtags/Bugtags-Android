package com.bugtag.networkapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by bugtags.com on 16/3/8.
 */
public class OkhttpTest {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "OkhttpTest";

    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void getHtml() {


        Log.d(TAG, "content-type:");
//        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";
//        try {
//            URL url = new URL(urlStr);
//
//            HttpURLConnection connection = okHttpClient.open(url);
//
//            connection.setDoInput(true);
//            connection.setRequestMethod("GET");
//            connection.addRequestProperty("a", "b");
//            connection.addRequestProperty("a", "c");
//
//            connection.connect();
//
//            Log.d(TAG, "content-type:" + connection.getContentType());
//
//            InputStream is = connection.getInputStream();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            String line = null;
//            StringBuffer sb = new StringBuffer();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            is.close();
//
//            Log.d(TAG, sb.toString());
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void getJson() {

    }

    public static void getFile(final View view) {

    }


    public static void postUrlencode() {

    }

    public static void postMultipart(Activity activity) {

    }
}
