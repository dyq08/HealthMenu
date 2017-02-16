package com.ding.volley;

import android.content.Context;

/**
 * Created by Administrator on 2016/9/22.
 */

public abstract class BaseRequest<T extends BaseRequest> {
    protected String url;
    protected Context context;
    protected boolean isLoading = false;
    protected String loadingTitle;
    protected String params;
    protected boolean loadingBack = true;
    protected int timeOut = 30000;
    public T timeOut(int time) {
        this.timeOut = time;
        return (T) this;
    }
    public T loading(boolean loading) {
        this.isLoading = loading;
        return (T) this;
    }

    public T loadingTitle(String title) {
        this.loadingTitle = title;
        return (T) this;
    }

    public T loadingBack(boolean loadingBack) {
        this.loadingBack = loadingBack;
        return (T) this;
    }
}
