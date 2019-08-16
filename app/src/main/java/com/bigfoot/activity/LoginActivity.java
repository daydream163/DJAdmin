package com.bigfoot.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigfoot.R;
import com.bigfoot.http.HttpClient;
import com.bigfoot.http.HttpResponseHandler;
import com.bigfoot.http.RestApiResponse;
import com.bigfoot.ui.UIHelper;
import com.bigfoot.ui.swipebacklayout.SwipeBackActivity;
import com.bigfoot.utils.SharedPreferences;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.System.exit;


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
                exit(0);
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
                EditText user = (EditText) findViewById(R.id.user);
                EditText password = (EditText) findViewById(R.id.password);
                login(user.getText().toString(), password.getText().toString());
            }
        });
    }

    private void initView() {

    }

    private void login(final String user, final String password) {

        Map<String, String> param = new LinkedHashMap<>();
        param.put("mobile", user);
        param.put("password", password);

        HttpClient.form("https://www.meiminger.com/api/common/managerlogin", param, new HttpResponseHandler() {
            @Override
            public void onSuccess(RestApiResponse response) {
                if(response.isStatus()) {
                    UIHelper.showHome(LoginActivity.this);
                    SharedPreferences.getInstance().putString("token", response.getData().toString());
                } else {
                    Toasty.error(LoginActivity.this, response.getMsg(), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Request request, Exception e) {
                Toasty.error(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT, true).show();
            }
        });
    }
}
