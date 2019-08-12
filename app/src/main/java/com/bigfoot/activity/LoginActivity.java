package com.bigfoot.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigfoot.R;
import com.bigfoot.http.HttpClient;
import com.bigfoot.http.HttpResponseHandler;
import com.bigfoot.http.RestApiResponse;
import com.bigfoot.ui.UIHelper;
import com.bigfoot.ui.swipebacklayout.SwipeBackActivity;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Request;


/**
 * Created by tiansj on 15/7/31.
 */
public class LoginActivity extends SwipeBackActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnOtherLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.layoutUser).setVisibility(View.GONE);
                findViewById(R.id.layoutPhone).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutPwd).setVisibility(View.GONE);
                findViewById(R.id.layoutCode).setVisibility(View.VISIBLE);
                findViewById(R.id.btnOtherLogin).setVisibility(View.GONE);

                btnBack = (Button) findViewById(R.id.btnBack);
                btnBack.setVisibility(View.VISIBLE);

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.layoutUser).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutPhone).setVisibility(View.GONE);
                        findViewById(R.id.layoutPwd).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutCode).setVisibility(View.GONE);
                        findViewById(R.id.btnOtherLogin).setVisibility(View.VISIBLE);

                        btnBack.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        findViewById(R.id.btnSure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                login();
                UIHelper.showHome(LoginActivity.this);
            }
        });
    }

    private void initView() {

    }

    private void login() {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("username", "rocday");
        param.put("password", "123456");
        HttpClient.post("https://www.meiminger.com/api/common/managerlogin.htm", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                UIHelper.showHome(LoginActivity.this);
            }

            @Override
            public void onFailure(Request request, Exception e) {
                UIHelper.showHome(LoginActivity.this);
            }
        });
    }
}
