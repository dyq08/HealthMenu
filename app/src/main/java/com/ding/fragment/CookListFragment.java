package com.ding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.ding.adapter.CookAdapter;
import com.ding.bean.CookListBean;
import com.ding.activity.DetailsActivity;
import com.ding.healthmenu.R;
import com.ding.net.Constants;
import com.ding.net.URLApi;
import com.ding.volley.IRequest;
import com.ding.volley.RequestJsonListener;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


/**
 *
 */
public class CookListFragment extends Fragment implements AdapterView.OnItemClickListener {
    public String mCookId = "";
    private PtrClassicFrameLayout vPtrFrameLayout;
    private View vHintNet;
    private View vHintData;
    private ListView vCookList = null;
    private CookAdapter mAdapter;
    private List<CookListBean> mCookListBeans = new ArrayList<>();
    private int lastVisibilityItem;
    private int page = 1;
    private LinearLayout vFooter;

    public CookListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_cook_list, container, false);
        vPtrFrameLayout = (PtrClassicFrameLayout) vView.findViewById(R.id.load_more_ptr);
        vHintNet = vView.findViewById(R.id.hint_net);
        vHintData = vView.findViewById(R.id.hint_data);
        vCookList = (ListView) vView.findViewById(R.id.cook_list);
        return vView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFooter();
        mAdapter = new CookAdapter(getContext(), mCookListBeans);
        vCookList.setAdapter(mAdapter);
        vCookList.setOnItemClickListener(this);
        vPtrFrameLayout.setLoadingMinTime(1000);
        vPtrFrameLayout.setLastUpdateTimeRelateObject(this);
        vPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame,
                                             View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                        vCookList, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1;
                loadData();
            }
        });
        vPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                vPtrFrameLayout.autoRefresh(false);
            }
        }, 150);

        vCookList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && lastVisibilityItem == mCookListBeans.size()) {
                    page = page + 1;
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                lastVisibilityItem = firstVisibleItem + visibleItemCount - 1;
            }
        });

        vHintNet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Constants.isFastClick()) {
                    return;
                }
                loadData();
                vPtrFrameLayout.autoRefresh(false);
            }
        });
    }

    private void loadData() {
//        page	否	int	请求页数，默认page=1
//        rows	否	int	每页返回的条数，默认rows=20
//        id	否	int	分类ID，默认返回的是全部。这里的ID就是指分类
        IRequest.get(getContext(), URLApi.HEALTH_LIST + "?id=" + mCookId + "&page=" + page).execute(CookListBean.class, new RequestJsonListener<CookListBean>() {
            @Override
            public void onSuccess(CookListBean result) {
                if (page == 1) {
                    vPtrFrameLayout.refreshComplete();
                }

                vHintNet.setVisibility(View.GONE);
                if (result.isStatus()) {
                    if (page == 1) {
                        mCookListBeans.clear();
                        mCookListBeans.addAll(result.getTngou());
                        if (mCookListBeans.size() == 0) {
                            vHintData.setVisibility(View.VISIBLE);
                        } else {
                            vHintData.setVisibility(View.GONE);
                        }
                    } else {
                        vFooter.setVisibility(View.VISIBLE);
                        mCookListBeans.addAll(result.getTngou());
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(VolleyError e) {
                super.onError(e);
                if ((page == 1) && (mCookListBeans.size()) == 0) {
                    vHintNet.setVisibility(View.VISIBLE);
                    vHintData.setVisibility(View.GONE);
                }
                vPtrFrameLayout.refreshComplete();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Constants.isFastClick()) {
            return;
        }
        if (position != mCookListBeans.size()) {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("cook_id", mCookListBeans.get(position).getId());
            getActivity().startActivity(intent);
        }
    }

    private void setFooter() {
        vFooter = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.footer, null);
        vFooter.setVisibility(View.GONE);
        vCookList.addFooterView(vFooter);
    }
}