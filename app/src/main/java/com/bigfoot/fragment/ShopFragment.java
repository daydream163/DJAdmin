package com.bigfoot.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bigfoot.R;
import com.bigfoot.http.HttpClient;
import com.bigfoot.http.HttpResponseHandler;
import com.bigfoot.http.RestApiResponse;
import com.bigfoot.ui.UIHelper;
import com.bigfoot.utils.SharedPreferences;

import java.util.LinkedHashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Request;

public class ShopFragment extends Fragment {
    private Activity context;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return root = inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        initView();
        initDataOrders();
        initDataGoods();
    }

    void initView() {
//        TextView head = root.findViewById(R.id.textHeadTitle);
//        head.setText("张三的店");

        root.findViewById(R.id.layDeals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showDealsActivity(context);
            }
        });

        root.findViewById(R.id.layOrders).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showOrdersActivity(context);
            }
        });

        root.findViewById(R.id.layUnpaid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showOrdersListActivity(context, 1);
            }
        });

        root.findViewById(R.id.layOverhang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showOrdersListActivity(context, 2);
            }
        });

        root.findViewById(R.id.layShipped).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showOrdersListActivity(context, 3);
            }
        });

        root.findViewById(R.id.ibArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showOrdersListActivity(context, 4);
            }
        });

        root.findViewById(R.id.layAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showGoodsListActivity(context, 1);
            }
        });

        root.findViewById(R.id.laySelling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showGoodsListActivity(context, 2);
            }
        });

        root.findViewById(R.id.laySoldout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showGoodsListActivity(context, 3);
            }
        });

        root.findViewById(R.id.layTostayon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showGoodsListActivity(context, 4);
            }
        });

        root.findViewById(R.id.ibAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showSingleProductActiviy(context);
            }
        });
    }

    private void initDataOrders() {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("token", SharedPreferences.getInstance().getString("token", null));

        HttpClient.form("https://www.meiminger.com/api/apporder/getstatis", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if(response.isStatus()) {
                    updateOrdersView(JSONObject.parseObject(response.getData().toString()));
                } else {
                    Toasty.error(context, response.getMsg(), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {
                Toasty.error(context, "数据加载失败", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void updateOrdersView(JSONObject data) {
        TextView unpaid = root.findViewById(R.id.unpaid);
        unpaid.setText(data.getString("payment"));

        TextView overhang = root.findViewById(R.id.overhang);
        overhang.setText(data.getString("delivered"));

        TextView shipped = root.findViewById(R.id.shipped);
        shipped.setText(data.getString("receive"));
    }

    private void initDataGoods() {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("token", SharedPreferences.getInstance().getString("token", null));

        HttpClient.form("https://www.meiminger.com/api/appgoods/getstatis", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if(response.isStatus()) {
                    updateGoodsView(JSONObject.parseObject(response.getData().toString()));
                } else {
                    Toasty.error(context, response.getMsg(), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {
                Toasty.error(context, "数据加载失败", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void updateGoodsView(JSONObject data) {
        TextView all = root.findViewById(R.id.all);
        all.setText(data.getString("totalGoods"));

        TextView selling = root.findViewById(R.id.selling);
        selling.setText(data.getString("totalMarketableUp"));

        TextView soldout = root.findViewById(R.id.soldout);
        soldout.setText(data.getString("totalMarketableDown"));
    }
}
