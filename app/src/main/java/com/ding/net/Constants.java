package com.ding.net;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.ding.healthmenu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Administrator on 2016/9/23.
 */

public class Constants {
    private static long lastClickTime;

    public static String[] id = {"1", "10", "15", "52", "62", "68", "75", "82", "98", "112", "132", "147", "161", "166", "182", "188", "192", "197", "202", "205", "212", "218", "227"};

    public static String getId() {
        return "http://tnfs.tngou.net/image";
    }

    public static String getImage() {
        return "http://tnfs.tngou.net/image";
    }

    public static int getWidth(Activity mActivity) {
        // 获取屏幕分辨率
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public static int getHeight(Activity mActivity) {
        // 获取屏幕分辨率
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.review_ic_review_score_tip_normal_off)
            .showImageOnFail(R.mipmap.review_ic_review_score_tip_normal_off)
            .showImageOnLoading(R.mipmap.review_ic_review_score_tip_normal_off).cacheInMemory(true)
            .cacheOnDisk(true).considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(5)).build();

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
