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

import com.bugtag.networkapp.retrofit.Api;
import com.bugtag.networkapp.retrofit.Model;
import com.bugtag.networkapp.retrofit.ToStringConverterFactory;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bugtags.com on 16/3/8.
 */
public class Retrofit2Test {

    private static final String HOST_PREFIX = "http://192.168.1.105:8080";
    private static final String TAG = "Okhttp3Test";
    static Handler handler = new Handler(Looper.getMainLooper());

    static OkHttpClient okHttpClient = new OkHttpClient();

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HOST_PREFIX)
            .addConverterFactory(new ToStringConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    static Api api = retrofit.create(Api.class);

    public static void getHtml() {
        api.getHtml().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                Log.d(TAG, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, call.toString());
            }
        });
    }

    public static void getJson() {

        api.getJson().enqueue(new Callback<Model.HttpResponse>() {
            @Override
            public void onResponse(Call<Model.HttpResponse> call, retrofit2.Response<Model.HttpResponse> response) {
                Log.d(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<Model.HttpResponse> call, Throwable t) {

                Log.d(TAG, call.toString());
            }
        });
    }

    public static void getFile(final View view) {

        api.getFile().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

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
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d(TAG, call.toString());
            }
        });

    }


    public static void postUrlencode() {
        Map<String, String> fields = new HashMap<>();
        fields.put("a", "b");
        fields.put("b", "c");

        api.postEncode(fields).enqueue(new Callback<Model.HttpResponse>() {
            @Override
            public void onResponse(Call<Model.HttpResponse> call, retrofit2.Response<Model.HttpResponse> response) {
                Log.d(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<Model.HttpResponse> call, Throwable t) {

                Log.d(TAG, call.toString());
            }
        });

    }

    public static void postMultipart(Activity activity) {
        //bitmap

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        //attachment
        String attachmentName = "bitmap";
        String attachmentFileName = "bitmap.png";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), stream.toByteArray());

//        RequestBody body = new MultipartBody.Builder().
//                setType(MultipartBody.FORM).
//                addFormDataPart(attachmentName, attachmentFileName, RequestBody.create(MediaType.parse("image/png"), pixels)).
//                build();

        api.postMulter(requestBody).enqueue(new Callback<Model.HttpResponse>() {
            @Override
            public void onResponse(Call<Model.HttpResponse> call, retrofit2.Response<Model.HttpResponse> response) {
                Log.d(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<Model.HttpResponse> call, Throwable t) {

                Log.d(TAG, call.toString());
            }
        });
    }
}
