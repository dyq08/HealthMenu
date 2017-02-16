package com.ding.volley;

import android.content.Context;

/**
 * Created by Administrator on 2016/9/22.
 */

public class GetRequest extends BaseRequest<GetRequest> {


    public GetRequest(Context context, String url) {
        this.url = url;
        this.context = context;
    }

    public <T> void execute(Class<T> classOfT, RequestJsonListener<T> l) {
        RequestManager.get(context, url, isLoading, loadingTitle, loadingBack, classOfT, timeOut, l);
    }

}
