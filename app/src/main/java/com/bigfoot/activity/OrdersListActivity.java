package com.bigfoot.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.bigfoot.R;
import com.bigfoot.http.HttpClient;
import com.bigfoot.http.HttpResponseHandler;
import com.bigfoot.http.RestApiResponse;
import com.bigfoot.model.OrdersList;
import com.bigfoot.model.SearchParam;
import com.bigfoot.ui.UIHelper;
import com.bigfoot.ui.pulltorefresh.PullToRefreshBase;
import com.bigfoot.ui.pulltorefresh.PullToRefreshListView;
import com.bigfoot.ui.quickadapter.BaseAdapterHelper;
import com.bigfoot.ui.quickadapter.QuickAdapter;
import com.bigfoot.utils.SharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

public class OrdersListActivity extends BaseFragmentActivity {
    private SearchParam param;
    private int pno = 1;
    private boolean isLoadAll;
    private int type;

    @Bind(R.id.listView)
    PullToRefreshListView listView;
    QuickAdapter<OrdersList> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list_view);
        ButterKnife.bind(this);
        type = getGoodsType();
        initData();
        initView();
        loadData();
    }

    private int getGoodsType() {
        Bundle bundle = getIntent().getExtras();
        return bundle.getInt("type");
    }

    private void initData() {
        param = new SearchParam();
        pno = 1;
        isLoadAll = false;
    }

    private void initView() {
        adapter = new QuickAdapter<OrdersList>(OrdersListActivity.this, R.layout.activity_orders_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, OrdersList orders) {
                helper.setText(R.id.tvOrderId, orders.getOrder_id())
                        .setText(R.id.tvClient, orders.getShip_name() + " " + orders.getShip_mobile())
                        .setText(R.id.tvAddress, orders.getArea_name())
//                        .setText(R.id.tvDelivery, )
                        .setText(R.id.tvExpress, orders.getShip_status())
                ;
            }
        };

        listView.withLoadMoreView();
        listView.setAdapter(adapter);
        // 下拉刷新
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
                loadData();
            }
        });
        // 加载更多
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadData();
            }
        });
        // 点击事件
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UIHelper.showHouseDetailActivity(OrdersListActivity.this);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    Picasso.with(OrdersListActivity.this).pauseTag(OrdersListActivity.this);
                } else {
                    Picasso.with(OrdersListActivity.this).resumeTag(OrdersListActivity.this);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private void loadData() {
        if (isLoadAll) {
            return;
        }
        param.setPno(pno);
        listView.setLoadMoreViewTextLoading();

        Map<String, String> param = new LinkedHashMap<>();
        param.put("token", SharedPreferences.getInstance().getString("token", null));

        HttpClient.form("https://www.meiminger.com/api/apporder/getlist", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if (response.isStatus()) {
                    listView.onRefreshComplete();
                    List<OrdersList> list = JSONArray.parseArray(response.getData().toString(), OrdersList.class);
                    listView.updateLoadMoreViewText(list);
                    isLoadAll = list.size() < HttpClient.PAGE_SIZE;
                    if (pno == 1) {
                        adapter.clear();
                    }
                    adapter.addAll(list);
                    pno++;
                } else {
                    listView.onRefreshComplete();
                    listView.setLoadMoreViewTextError();
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {
                listView.onRefreshComplete();
                listView.setLoadMoreViewTextError();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Picasso.with(OrdersListActivity.this).resumeTag(OrdersListActivity.this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Picasso.with(OrdersListActivity.this).pauseTag(OrdersListActivity.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Picasso.with(OrdersListActivity.this).cancelTag(OrdersListActivity.this);
    }

}
