package com.bugtag.networkapp.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by bugtags.com on 16/4/27.
 */
public class GzipStringRequest extends ExtendedRequest<String> {
    public GzipStringRequest(String url, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, url, successListener, errorListener);
    }

    @Override
    protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {
        String jsonString;
        try {
            jsonString = getResponseString(response);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError(e));
        }

        return com.android.volley.Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
    }
}
