package com.ding.volley;

/**
 * Created by Administrator on 2016/9/22.
 */

public abstract class RequestJsonListener<T> extends BaseRequestListener {
    /**
     * 成功
     */
    public abstract void onSuccess(T result);

}
