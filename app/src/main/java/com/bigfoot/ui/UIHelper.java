package com.bigfoot.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bigfoot.activity.DealsActivity;
import com.bigfoot.activity.GoodsActivity;
import com.bigfoot.activity.HouseDetailActivity;
import com.bigfoot.activity.LoginActivity;
import com.bigfoot.activity.MainActivity;
import com.bigfoot.activity.OrdersActivity;
import com.bigfoot.activity.SingleProductActiviy;

/**
 * 应用程序UI工具包：封装UI相关的一些操作
 */
public class UIHelper {

    public final static String TAG = "UIHelper";

    public final static int RESULT_OK = 0x00;
    public final static int REQUEST_CODE = 0x01;

    public static void ToastMessage(Context cont, String msg) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        if (cont == null || msg <= 0) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, String msg, int time) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, time).show();
    }

    public static void showHome(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void showLogin(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    public static void showHouseDetailActivity(Activity context) {
        Intent intent = new Intent(context, HouseDetailActivity.class);
        context.startActivity(intent);
    }

    public static void showSingleProductActiviy(Activity context) {
        Intent intent = new Intent(context, SingleProductActiviy.class);
        context.startActivity(intent);
        context.finish();
    }

    public static void showDealsActivity(Activity context) {
        Intent intent = new Intent(context, DealsActivity.class);
        context.startActivity(intent);
    }

    public static void showOrdersActivity(Activity context) {
        Intent intent = new Intent(context, OrdersActivity.class);
        context.startActivity(intent);
    }

    public static void showGoodsActivity(Activity context, int type) {
        Intent intent = new Intent(context, GoodsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
