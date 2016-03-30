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

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bugtags.com on 16/2/23.
 */
public class HttpClientTest {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "HttpURLConnectionTest";
    static Handler handler = new Handler(Looper.getMainLooper());
    static HttpClient httpClient = new DefaultHttpClient();

    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html";
        urlStr = "http://baidu.com";
        HttpGet get = new HttpGet(urlStr);
        try {
            HttpHost host = new HttpHost(urlStr);
            get.addHeader("a","c");
            get.setHeader("a","b");

            String str = httpClient.execute(host, get, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    HttpEntity entity = httpResponse.getEntity();

                    InputStream is = entity.getContent();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String line;
                    StringBuffer sb = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    is.close();
                    return sb.toString();
                }
            });


            Log.d(TAG, str.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getJson() {
        String urlStr = HOST_PREFIX + "/get/json";
        urlStr = "http://baidu.com";

        HttpGet get = new HttpGet(urlStr);
        try {
            get.addHeader("a","c");
            get.setHeader("a","b");
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            is.close();
            Log.d(TAG, sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFile(final View view) {
        String urlStr = HOST_PREFIX + "/get/file";
        urlStr = "http://baidu.com";
        HttpGet get = new HttpGet(urlStr);
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();

            final Bitmap bitmap = BitmapFactory.decodeStream(is);

            if (bitmap != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((Button) view).setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            }

            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void postUrlencode() {
        String urlStr = HOST_PREFIX + "/post/encode";
        urlStr = "http://baidu.com";

        HttpPost post = new HttpPost(urlStr);
        post.addHeader("a","c");
        post.setHeader("a","b");

        List<BasicNameValuePair> pairs = new ArrayList<>();
        //name=hello&p1=1&p2=2
        pairs.add(new BasicNameValuePair("name", "hello"));
        pairs.add(new BasicNameValuePair("p1", "1"));
        pairs.add(new BasicNameValuePair("p2", "2"));

        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
            post.setEntity(urlEncodedFormEntity);

            HttpResponse response = httpClient.execute(post);

            post.addHeader("a","c");

            InputStream is = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            is.close();
            Log.d(TAG, sb.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void postMultipart(Activity activity) {
        String urlStr = HOST_PREFIX + "/post/multer";
        urlStr = "http://baidu.com";

        HttpPost post = new HttpPost(urlStr);
        post.addHeader("a","c");
        post.setHeader("a","b");

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody(attachmentName, new ByteArrayInputStream(stream.toByteArray()), ContentType.DEFAULT_BINARY, attachmentFileName);

        post.setEntity(builder.build());

        try {
            HttpResponse response = httpClient.execute(post);
            InputStream is = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            is.close();
            Log.d(TAG, sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
