package com.ding.healthmenu;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/22.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    public void setContentView(int layoutResID) {
        // TODO Auto-generated method stub
        super.setContentView(layoutResID);
        initViews();
        initEvents();
    }

    @Override
    public void setContentView(View view) {
        // TODO Auto-generated method stub
        super.setContentView(view);
        initViews();
        initEvents();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        // TODO Auto-generated method stub
        super.setContentView(view, params);
        initViews();
        initEvents();
    }

    /**
     * 初始化视图
     **/
    protected abstract void initViews();

    /**
     * 初始化事件
     **/
    protected abstract void initEvents();
}
