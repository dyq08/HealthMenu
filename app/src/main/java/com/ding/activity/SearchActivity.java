package com.ding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.ding.adapter.CookAdapter;
import com.ding.bean.CookListBean;
import com.ding.healthmenu.BaseActivity;
import com.ding.healthmenu.R;
import com.ding.net.Constants;
import com.ding.net.URLApi;
import com.ding.volley.IRequest;
import com.ding.volley.RequestJsonListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Button vBackBtn;
    private EditText vSearchEdit;
    private Button vSearchCloseBtn;
    private Button vSearchBtn;
    private View vHintNet;
    private View vHintData;
    private ListView vCookList = null;
    private CookAdapter mAdapter;
    private List<CookListBean> mCookListBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initViews() {
        vBackBtn = (Button) findViewById(R.id.back_btn);
        vSearchEdit = (EditText) findViewById(R.id.search_edit);
        vSearchCloseBtn = (Button) findViewById(R.id.search_close_btn);
        vSearchBtn = (Button) findViewById(R.id.search_btn);
        vHintNet = findViewById(R.id.hint_net);
        vHintData = findViewById(R.id.hint_data);
        vCookList = (ListView) findViewById(R.id.cook_list);
    }

    @Override
    protected void initEvents() {
        mAdapter = new CookAdapter(this, mCookListBeans);
        vCookList.setAdapter(mAdapter);
        vCookList.setOnItemClickListener(this);
        vBackBtn.setOnClickListener(this);
        vSearchCloseBtn.setOnClickListener(this);
        vSearchBtn.setOnClickListener(this);

        vSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    vSearchCloseBtn.setVisibility(View.GONE);
                } else {
                    vSearchCloseBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Constants.isFastClick()) {
            return;
        }
        if (position != mCookListBeans.size()) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("cook_id", mCookListBeans.get(position).getId());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (Constants.isFastClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.search_btn:
                if (!vSearchEdit.getText().toString().isEmpty()) {
                    loadData(vSearchEdit.getText().toString());
                } else {
                    Toast.makeText(this, "请输入菜名", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.search_close_btn:
                vSearchEdit.setText("");
                break;
            case R.id.back_btn:
                finish();
                break;
            default:
                break;
        }
    }

    private void loadData(String key) {
        IRequest.get(this, URLApi.HEALTH_NAME + "?name=" + key).loading(true).execute(CookListBean.class, new RequestJsonListener<CookListBean>() {
            @Override
            public void onSuccess(CookListBean result) {
                vHintNet.setVisibility(View.GONE);
                if (result.isStatus()) {
                    if (mCookListBeans.size() == 0) {
                        vHintData.setVisibility(View.VISIBLE);
                    } else {
                        vHintData.setVisibility(View.GONE);
                    }
                    mCookListBeans.clear();
                    mCookListBeans.addAll(result.getTngou());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(VolleyError e) {
                super.onError(e);
                if (mCookListBeans.size() == 0) {
                    vHintNet.setVisibility(View.VISIBLE);
                    vHintData.setVisibility(View.GONE);
                }
            }
        });
    }
}
