package com.ding.bean;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class Result<T> {
    private T tngou;
    private boolean status;

    public T getTngou() {
        return tngou;
    }

    public void setTngou(T tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
