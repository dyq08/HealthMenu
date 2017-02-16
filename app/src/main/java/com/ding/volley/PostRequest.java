package com.ding.volley;

import android.content.Context;

import com.ding.util.JsonUtil;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class PostRequest extends BaseRequest<PostRequest> {

    public <T>PostRequest(Context context, String url, T params) {
        this.url = url;
        this.context = context;
        this.params= JsonUtil.string(params);
    }

    @SuppressWarnings("unchecked")
    public <T> void execute(Class<T> classOfT, RequestJsonListener<T> l) {
        RequestManager.post(context, url, isLoading, loadingTitle,loadingBack, classOfT,timeOut, l);
    }

}
