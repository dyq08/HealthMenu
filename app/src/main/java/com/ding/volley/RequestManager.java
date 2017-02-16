package com.ding.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ding.healthmenu.HealthApplication;
import com.ding.widget.LoadingDialog;

import java.io.UnsupportedEncodingException;

import okhttp3.OkHttpClient;

/**
 * Created by 丁应清 on 2016/9/22.
 */
public class RequestManager {
    private static RequestQueue mRequestQueue;

    private static RequestQueue getInstance() {
        if (mRequestQueue == null) {
            synchronized (RequestManager.class) {
                mRequestQueue = Volley.newRequestQueue(HealthApplication.getContext(), new OkHttpStack(new OkHttpClient.Builder().build()));
            }
        }
        return mRequestQueue;
    }

    /**
     * @param context       上下文
     * @param url           地址
     * @param loadingShow   true (显示进度) false (不显示进度)
     * @param progressTitle 进度条文字
     * @param classOfT      类对象
     * @param listener      回调
     * @param <T>
     */
    public static <T> void get(Context context, String url,boolean loadingShow, String progressTitle, boolean loadingBack, Class<T> classOfT, int timeOut, RequestJsonListener<T> listener) {
        LoadingDialog dialog = new LoadingDialog(context);
        try {
            dialog.setColose(loadingBack);
            if (loadingShow) {
                if (!TextUtils.isEmpty(progressTitle)) {
                    dialog.setLoadText(progressTitle);
                }
                dialog.show();
            }
        } catch (Exception e) {

        }

        GsonRequest request = new GsonRequest(Request.Method.GET,
                url, classOfT,
                responseListener(listener, loadingShow, dialog),
                responseError(listener, loadingShow, dialog));
        addRequest(request, context, timeOut);
    }

    /**
     * @param context       上下文
     * @param url           地址
     * @param loadingShow   true (显示进度) false (不显示进度)
     * @param progressTitle 进度条文字
     * @param classOfT      类对象
     * @param listener      回调
     * @param <T>
     */
    public static <T> void post(Context context, String url,boolean loadingShow, String progressTitle, boolean loadingBack, Class<T> classOfT, int timeOut, RequestJsonListener<T> listener) {
        LoadingDialog dialog = new LoadingDialog(context);
        try {
            dialog.setColose(loadingBack);
            if (loadingShow) {
                if (!TextUtils.isEmpty(progressTitle)) {
                    dialog.setLoadText(progressTitle);
                }
                dialog.show();
            }
        } catch (Exception e) {

        }

        GsonRequest request = new GsonRequest(Request.Method.POST,
                url, classOfT,
                responseListener(listener, loadingShow, dialog),
                responseError(listener, loadingShow, dialog));
        addRequest(request, context, timeOut);
    }

    /**
     * 成功消息监听 返回对象
     *
     * @param l
     * @return
     */
    protected static <T> Response.Listener<T> responseListener(
            final RequestJsonListener<T> l,
            final boolean flag, final LoadingDialog p) {
        return new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                if (flag) {
                    if (p.isShowing() && p != null) {
                        p.dismiss();
                    }
                }
                if (p.isShowing() && p != null) {
                    p.dismiss();
                }
                l.onSuccess(response);
            }
        };
    }

    /**
     * 对象返回错误监听
     *
     * @param l    回调
     * @param flag flag true 带进度条 flase不带进度条
     * @param p    进度条的对象
     * @return
     */
    protected static <T> Response.ErrorListener responseError(
            final RequestJsonListener<T> l, final boolean flag,
            final LoadingDialog p) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e) {
                l.onError(e);
                if (flag) {
                    if (p.isShowing()) {
                        p.dismiss();
                    }
                }
            }
        };
    }

    public static void addRequest(Request<?> request, Object tag, int timeOut) {
        if (tag != null) {
            request.setTag(tag);
        }
        request.setRetryPolicy(new DefaultRetryPolicy(timeOut == 0 ? 30000 : timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getInstance().add(request);
    }

    /**
     * 当主页面调用协议 在结束该页面调用此方法
     *
     * @param tag
     */
    public static void cancelAll(Object tag) {
        getInstance().cancelAll(tag);
    }
}