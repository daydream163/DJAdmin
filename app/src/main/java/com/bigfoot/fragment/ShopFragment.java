package com.bigfoot.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigfoot.R;

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
    }

    void initView() {
//        TextView head = root.findViewById(R.id.textHeadTitle);
//        head.setText("张三的店");

        root.findViewById(R.id.layArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        root.findViewById(R.id.layAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
