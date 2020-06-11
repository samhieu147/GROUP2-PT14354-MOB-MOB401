package com.example.develop.appquanlichitieu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.develop.appquanlichitieu.R;


public class ThonKeFragment extends Fragment implements View.OnClickListener {


    CardView today,thisweek,thismonth,thisyear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

       return view;

    }

    @Override
    public void onClick(View view) {

    }
}
