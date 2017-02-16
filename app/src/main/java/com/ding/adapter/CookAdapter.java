package com.ding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ding.bean.CookListBean;
import com.ding.bean.FoodClassBean;
import com.ding.healthmenu.R;
import com.ding.net.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 丁应清 on 2016/9/22.
 */

public class CookAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<CookListBean> mCookListBean = null;

    public CookAdapter(Context mContext, List<CookListBean> mCookListBean) {
        this.mContext = mContext;
        this.mCookListBean = mCookListBean;
    }

    @Override
    public int getCount() {
        return mCookListBean.size();
    }

    @Override
    public Object getItem(int position) {
        return mCookListBean.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cook_adapter, null);
            mHolder.vCookImg = (ImageView) convertView.findViewById(R.id.cook_img);
            mHolder.vNameText = (TextView) convertView.findViewById(R.id.name_text);
            mHolder.vDetailsText = (TextView) convertView.findViewById(R.id.details_text);
        }
        ImageLoader.getInstance().displayImage(Constants.getImage() + mCookListBean.get(position).getImg(), mHolder.vCookImg, Constants.options);
        mHolder.vNameText.setText(mCookListBean.get(position).getName());
        mHolder.vDetailsText.setText(mCookListBean.get(position).getDescription());
        return convertView;
    }

    private class ViewHolder {
        ImageView vCookImg;
        TextView vNameText;
        TextView vDetailsText;
    }

}
