package com.bugtag.networkapp.retrofit;

/**
 * Created by bugtags.com on 16/3/25.
 */
public class Model {
    public static class HttpResponse {
        int ret;
        int code;

        @Override
        public String toString() {
            return super.toString() + " ret:" + ret + " code:" + code;
        }
    }

}
