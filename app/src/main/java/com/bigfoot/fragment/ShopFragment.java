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
import com.bigfoot.activity.LoginActivity;
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
        initData();
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

        root.findViewById(R.id.ibArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        root.findViewById(R.id.ibAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showSingleProductActiviy(context);
            }
        });
    }

    private void initData() {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("token", SharedPreferences.getInstance().getString("token", null));

        HttpClient.form("https://www.meiminger.com/api/apporder/getstatis", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if(response.isStatus()) {
                    updateView(JSONObject.parseObject(response.getData().toString()));
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

    private void updateView(JSONObject data) {
        TextView unpaid = root.findViewById(R.id.unpaid);
        unpaid.setText(data.getString("payment"));

        TextView overhang = root.findViewById(R.id.overhang);
        overhang.setText(data.getString("delivered"));

        TextView shipped = root.findViewById(R.id.shipped);
        shipped.setText(data.getString("receive"));
    }
}
