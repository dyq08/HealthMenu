package com.ding.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ding.bean.CookListBean;
import com.ding.healthmenu.BaseActivity;
import com.ding.healthmenu.R;
import com.ding.net.Constants;
import com.ding.net.URLApi;
import com.ding.util.JsonUtil;
import com.ding.volley.IRequest;
import com.ding.volley.RequestJsonListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {
    private int mCookId = 0;
    private Button vBackBtn;
    private ImageView vCookImg;
    private TextView vNameText;
    private TextView vFootText;
    private TextView vPracticeText;
    private WebView vWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCookId = getIntent().getIntExtra("cook_id", 0);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void initViews() {
        vBackBtn = (Button) findViewById(R.id.back_btn);
        vCookImg = (ImageView) findViewById(R.id.cook_img);
        vNameText = (TextView) findViewById(R.id.name_text);
        vFootText = (TextView) findViewById(R.id.food_text);
        vPracticeText = (TextView) findViewById(R.id.practice_text);
        vWebView = (WebView) findViewById(R.id.web_view);
    }

    @Override
    protected void initEvents() {
        vBackBtn.setOnClickListener(this);
        loadData();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void loadData() {
        IRequest.get(this, URLApi.HEALTH_SHOW + "?id=" + mCookId).loading(true).execute(CookListBean.class, new RequestJsonListener<CookListBean>() {
            @Override
            public void onSuccess(CookListBean result) {
                if (result.isStatus()) {
                    Log.e("tag", JsonUtil.string(result));
                    vCookImg.setLayoutParams(getLayoutParams());
                    ImageLoader.getInstance().displayImage(Constants.getImage() + result.getImg(), vCookImg, Constants.options);
                    vNameText.setText(result.getName());
                    vFootText.setText(result.getFood());
                    vPracticeText.setText(result.getName() + "的做法");
                    vWebView.loadDataWithBaseURL(null, result.getMessage(), "text/html", "utf-8", null);
                }
            }
        });
    }

    /**
     * 图片大小
     *
     * @return
     */
    private LinearLayout.LayoutParams getLayoutParams() {
        int width = Constants.getWidth(this);
        int height = 180 * width / 270;
        return new LinearLayout.LayoutParams(width, height);
    }
}
