package com.ding.volley;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016/9/22.
 */

public class BaseRequestListener {
    /**
     * 错误
     */
    public void onError(VolleyError e) {
        //  android.util.Log.e("VolleyError",e.toString());
        //IToast.showToast_Fork(AppApplication.getContext(), "网络不可用，请检查网络");
    }
}
