package com.bigfoot.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigfoot.R;
import com.bigfoot.http.HttpClient;
import com.bigfoot.http.HttpResponseHandler;
import com.bigfoot.http.RestApiResponse;
import com.bigfoot.ui.UIHelper;
import com.bigfoot.ui.pulltozoomview.PullToZoomScrollViewEx;
import com.bigfoot.utils.SharedPreferences;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Request;

public class MemberFragment extends Fragment {

    private Activity context;
    private View root;
    private PullToZoomScrollViewEx scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return root = inflater.inflate(R.layout.fragment_member, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initData();
        initView();
    }

    void initView() {
        scrollView = root.findViewById(R.id.scrollView);
        View headView = LayoutInflater.from(context).inflate(R.layout.member_head_view, null, false);
        View zoomView = LayoutInflater.from(context).inflate(R.layout.member_zoom_view, null, false);
        View contentView = LayoutInflater.from(context).inflate(R.layout.member_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);


        scrollView.getPullRootView().findViewById(R.id.textUserSecurity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        scrollView.getPullRootView().findViewById(R.id.textSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

//        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
//        int mScreenHeight = localDisplayMetrics.heightPixels;
//        int mScreenWidth = localDisplayMetrics.widthPixels;
//        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
//        scrollView.setHeaderLayoutParams(localObject);
    }

    private void initData() {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("token", SharedPreferences.getInstance().getString("token", null));

        HttpClient.form("https://www.meiminger.com/api/common/getmngrbytoken", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if (response.isStatus()) {
                    UIHelper.ToastMessage(context, "获取用户信息成功");
                    TextView tvUserName = scrollView.getPullRootView().findViewById(R.id.tv_user_name);
                    JSONObject user = JSONObject.parseObject(response.getData().toString());
                    tvUserName.setText(user.getString("username"));
                } else {
                    UIHelper.ToastMessage(context, "获取用户信息失败");
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {
                UIHelper.ToastMessage(context, "获取用户信息失败");
            }
        });
    }
}