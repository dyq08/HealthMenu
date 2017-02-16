package com.ding.bean;

import java.util.List;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class ResultList<T> {
    private List<T> tngou;
    private boolean status;

    public List<T> getTngou() {
        return tngou;
    }

    public void setTngou(List<T> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
