package com.example.develop.appquanlichitieu.TabPage;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.develop.appquanlichitieu.Database.DatabaseLoaiThu;
import com.example.develop.appquanlichitieu.Model.LoaiThu;
import com.example.develop.appquanlichitieu.R;
import com.example.develop.appquanlichitieu.ViewHolder.LoaiThuAdapter;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;


public class LoaiThuFragment extends Fragment {
    RecyclerView recyclerView_LoaiThu;

    FloatingActionButton btnFab;
    FButton btnAdd, btnHuy;
    MaterialEditText edtName;
    LoaiThu loaiThu;
    DatabaseLoaiThu databaseLoaiThu;
    LoaiThuAdapter adapter;
    List<LoaiThu> listdata;

    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_thu, container, false);
        listdata = new ArrayList<>();
        recyclerView_LoaiThu = view.findViewById(R.id.recyclerview_loaithu);
        recyclerView_LoaiThu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView_LoaiThu.setLayoutManager(layoutManager);
        btnFab = view.findViewById(R.id.fab);

        databaseLoaiThu = new DatabaseLoaiThu(getContext());

        LoadDataLoaiThu();
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogAdd();
            }
        });
        return view;
    }


    private void ShowDialogAdd() {

        final Dialog add_loaithu_layout = new Dialog(getContext());
        add_loaithu_layout.setTitle("Thêm Loại Thu ");
        add_loaithu_layout.setCancelable(false);

        add_loaithu_layout.setContentView(R.layout.new_item_loaithu);
        add_loaithu_layout.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        btnAdd = add_loaithu_layout.findViewById(R.id.btnAdd);
        btnHuy = add_loaithu_layout.findViewById(R.id.btnHuy);
        edtName = add_loaithu_layout.findViewById(R.id.edtName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNew();
                add_loaithu_layout.cancel();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_loaithu_layout.cancel();
            }
        });
        add_loaithu_layout.show();
    }

    private void AddNew() {

        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng k để trống", Toast.LENGTH_SHORT).show();
        } else {
            loaiThu = new LoaiThu(edtName.getText().toString());
            long check = databaseLoaiThu.AddItem(loaiThu);
            LoadDataLoaiThu();
            Toast.makeText(getContext(), "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadDataLoaiThu() {
        listdata = databaseLoaiThu.getLoaiThu();
        adapter = new LoaiThuAdapter(listdata, getContext());
        recyclerView_LoaiThu.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


}
