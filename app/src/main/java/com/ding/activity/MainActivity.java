package com.ding.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ding.adapter.MyPagerAdapter;
import com.ding.bean.FoodClassBean;
import com.ding.fragment.CookListFragment01;
import com.ding.fragment.CookListFragment02;
import com.ding.fragment.CookListFragment03;
import com.ding.fragment.CookListFragment04;
import com.ding.fragment.CookListFragment05;
import com.ding.fragment.CookListFragment06;
import com.ding.fragment.CookListFragment07;
import com.ding.fragment.CookListFragment08;
import com.ding.fragment.CookListFragment09;
import com.ding.fragment.CookListFragment10;
import com.ding.fragment.CookListFragment11;
import com.ding.healthmenu.BaseActivity;
import com.ding.healthmenu.R;
import com.ding.net.URLApi;
import com.ding.service.RemindService;
import com.ding.util.JsonUtil;
import com.ding.volley.IRequest;
import com.ding.volley.RequestJsonListener;
import com.ding.widget.PagerSlidingTabStrip;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private PagerSlidingTabStrip vTabs = null;
    private ViewPager vViewPager = null;
    private Button vSearchBtn = null;
    private List<FoodClassBean> mFoodClassBeans = new ArrayList<>();
    private static final int SHOW_COUNT = 11; // 显示11个菜单项

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
//
//
//    ViewGroup bannerContainer;
//    BannerView bv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startService();
    }

    @Override
    protected void initViews() {
        vViewPager = (ViewPager) findViewById(R.id.view_pager);
        vTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        vSearchBtn = (Button) findViewById(R.id.search_btn);

//
//        bannerContainer = (ViewGroup) this.findViewById(R.id.bannerContainer);
    }
//
//    private void initBanner() {
//        this.bv = new BannerView(this, ADSize.BANNER, "1105648509", "7010312534734258");
//        bv.setRefresh(30);
//        bv.setADListener(new AbstractBannerADListener() {
//
//            @Override
//            public void onNoAD(int arg0) {
//                Log.e("tag", "BannerNoAD，eCode=" + arg0);
//            }
//
//            @Override
//            public void onADReceiv() {
//                Log.e("tag", "ONBannerReceive");
//            }
//        });
//        bannerContainer.addView(bv);
//    }

    @Override
    protected void initEvents() {
        vSearchBtn.setOnClickListener(this);
        try {
            String json = inputStream2String(getResources().getAssets().open("cook_class.txt"));
            FoodClassBean mFoodClassBean = JsonUtil.object(json, FoodClassBean.class);
            mFoodClassBeans.addAll(mFoodClassBean.getTngou());

            initData();
            setPager();
        } catch (IOException e) {

        }

//        this.initBanner();
//        this.bv.loadAD();

    }

    private void initData() {
        for (int i = 0; i < SHOW_COUNT; i++) {
            mTitles.add(mFoodClassBeans.get(i).getTitle());
        }

        mFragments.add(new CookListFragment01());
        mFragments.add(new CookListFragment02());
        mFragments.add(new CookListFragment03());
        mFragments.add(new CookListFragment04());
        mFragments.add(new CookListFragment05());
        mFragments.add(new CookListFragment06());
        mFragments.add(new CookListFragment07());
        mFragments.add(new CookListFragment08());
        mFragments.add(new CookListFragment09());
        mFragments.add(new CookListFragment10());
        mFragments.add(new CookListFragment11());
    }

    private void setPager() {
        vViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
        vTabs.setViewPager(vViewPager);
    }

    private String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    private void loadData() {
        IRequest.get(this, URLApi.HEALTH_CLASS_IFY).execute(FoodClassBean.class, new RequestJsonListener<FoodClassBean>() {
            @Override
            public void onSuccess(FoodClassBean result) {
                if (result.isStatus()) {
                    mFoodClassBeans.clear();
                    mFoodClassBeans.addAll(result.getTngou());
                    Log.e("tag", JsonUtil.string(result));
                }
            }
        });
    }

    /**
     * 开启服务
     */
    private void startService() {
        Intent startIntent = new Intent(this, RemindService.class);
        startService(startIntent);
    }
}
