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

import com.bugtag.networkapp.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * Created by bugtags.com on 16/2/22.
 */
public class HttpURLConnectionTest {

    private static final String HOST_PREFIX = "https://192.168.1.105";
    private static final String TAG = "HttpURLConnectionTest";
    static Handler handler = new Handler(Looper.getMainLooper());

    private static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public static void getHtml() {
        String urlStr = HOST_PREFIX + "/get/html?xxx=bb";
        urlStr = "http://baidu.com";
        try {

            trustEveryone();
            URL url = new URL(urlStr);

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.addRequestProperty("a", "b");
                connection.addRequestProperty("a", "c");
                String key = connection.getRequestProperty("a");

                connection.connect();

                Log.d(TAG, "content-type:" + connection.getContentType());

                InputStream is = connection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                is.close();

                Log.d(TAG, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void getJson() {
        trustEveryone();
        String urlStr = HOST_PREFIX + "/get/json?xxx=bb";
        urlStr = "http://baidu.com";
        try {
            URL url = new URL(urlStr);

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setDoInput(true);
                connection.connect();

                connection.setRequestMethod("GET");
                Log.d(TAG, "content-type:" + connection.getContentType());

                InputStream is = connection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                is.close();

                Log.d(TAG, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void getFile(final View view) {
        trustEveryone();
        String urlStr = HOST_PREFIX + "/get/file?xxx=bb";
        urlStr = "http://baidu.com";
        try {
            URL url = new URL(urlStr);

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setDoInput(true);
                connection.connect();

                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == 200) {
                    final Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    if (bitmap != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((Button) view).setBackgroundDrawable(new BitmapDrawable(bitmap));
                            }
                        });
                    }
                }

                connection.getInputStream().close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void postUrlencode() {
        trustEveryone();
        String urlStr = HOST_PREFIX + "/post/encode?xxx=bb";
        urlStr = "http://baidu.com";

        try {
            String urlParams = "name=hello&p1=1&p2=2";
            byte[] postData = urlParams.getBytes("UTF-8");
            int postDataLength = postData.length;
            URL url = new URL(urlStr);

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setDoOutput(true);
                connection.setDoInput(true);

                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", String.valueOf(postDataLength));

                connection.connect();

                connection.getOutputStream().write(postData);

                InputStream is = connection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                is.close();
                Log.d(TAG, sb.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void postMultipart(Activity activity) {
        trustEveryone();
        String urlStr = HOST_PREFIX + "/post/multer";
        urlStr = "http://baidu.com";
        //bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";
        String ctlf = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        //request
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlStr);

            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                httpURLConnection.connect();

                //content
                DataOutputStream request = new DataOutputStream(httpURLConnection.getOutputStream());
                request.writeBytes(twoHyphens + boundary + ctlf);
                request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\"; filename=\"" +
                        attachmentFileName + "\"" + ctlf);
                request.writeBytes(ctlf);

                //bitmap to byte
                byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
                for (int i = 0; i < bitmap.getWidth(); ++i) {
                    for (int j = 0; j < bitmap.getHeight(); ++j) {
                        //we're interested only in the MSB of the first byte,
                        //since the other 3 bytes are identical for B&W images
                        pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
                    }
                }

                request.write(pixels);

                //end post
                request.writeBytes(ctlf);
                request.writeBytes(twoHyphens + boundary + twoHyphens + ctlf);
                request.close();


                //input
                InputStream is = httpURLConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                is.close();

                Log.d(TAG, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
