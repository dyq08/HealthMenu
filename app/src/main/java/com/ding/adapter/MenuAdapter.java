package com.ding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ding.bean.FoodClassBean;
import com.ding.healthmenu.R;

import java.util.List;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class MenuAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<FoodClassBean> mFoodClassBeans = null;

    public MenuAdapter(Context mContext, List<FoodClassBean> mFoodClassBeans) {
        this.mContext = mContext;
        this.mFoodClassBeans = mFoodClassBeans;
    }

    @Override
    public int getCount() {
        return mFoodClassBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mFoodClassBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (mHolder == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_adapter, null);
            mHolder.vMenuText = (TextView) convertView.findViewById(R.id.menu_text);
        }
        mHolder.vMenuText.setText(mFoodClassBeans.get(position).getTitle());
        return convertView;
    }

    private class ViewHolder {
        TextView vMenuText;
    }

}
