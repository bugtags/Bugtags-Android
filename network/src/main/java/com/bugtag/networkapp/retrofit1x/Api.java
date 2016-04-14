package com.bugtag.networkapp.retrofit1x;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;

/**
 * Created by bugtags.com on 16/4/8.
 */
public interface Api {
    @GET("/get/html?xxx=bb")
    void getHtml(Callback<String> response);

    @GET("/get/json?xxx=bb")
    void getJson(Callback<String> response);

    @GET("/get/file?xxx=bb")
    void getFile(Callback<String> response);

    @FormUrlEncoded
    @POST("/post/encode?xxx=bb")
    void postEncode(Callback<String> response);

    @Multipart
    @POST("/post/multer")
    void postMulter(Callback<String> response);

}
