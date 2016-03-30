package com.bugtag.networkapp.retrofit;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by bugtags.com on 16/3/24.
 */
public interface Api {

    @GET("/get/html?xxx=bb")
    Call<String> getHtml();

    @GET("/get/json?xxx=bb")
    Call<Model.HttpResponse> getJson();

    @GET("/get/file?xxx=bb")
    Call<ResponseBody> getFile();

    @FormUrlEncoded
    @POST("/post/encode?xxx=bb")
    Call<Model.HttpResponse> postEncode(@FieldMap Map<String, String> fields);

    @Multipart
    @POST("/post/multer")
    Call<Model.HttpResponse> postMulter(@Part("bitmap\"; filename=\"bitmap.png\"") RequestBody file);
}
