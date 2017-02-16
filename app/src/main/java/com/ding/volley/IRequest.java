package com.ding.volley;

import android.content.Context;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class IRequest {

    /**
     * post请求
     */
    public static <T> PostRequest post(Context context, String url, T params) {
        return new <T>PostRequest(context, url, params);
    }

    /**
     * get请求
     */
    public static <T> GetRequest get(Context context, String url) {
        return new <T>GetRequest(context, url);
    }
}
