package com.example.develop.appquanlichitieu.Fragment;

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

import com.example.develop.appquanlichitieu.Database.DatabaseTaiKhoan;
import com.example.develop.appquanlichitieu.Model.TaiKhoan;
import com.example.develop.appquanlichitieu.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;


public class NapTienVaoTaiKhoanFragment extends Fragment {

    RecyclerView recyclerView_TaiKhoan;
    DatabaseTaiKhoan databaseTaiKhoan;
    TaiKhoan taiKhoan;
    List<TaiKhoan> listTaiKhoan;
    RecyclerView.LayoutManager layoutManager;
    MaterialEditText edtNameTK,edtSoTienTK;
    FButton btnHuy,btnAdd;
    FloatingActionButton btnAddTaiKhoan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_nap_tien_vao_tai_khoan,container,false);
        recyclerView_TaiKhoan=view.findViewById(R.id.recyclerview_taikhoan);
        btnAddTaiKhoan=view.findViewById(R.id.fabTaiKhoan);
        recyclerView_TaiKhoan.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView_TaiKhoan.setLayoutManager(layoutManager);
        databaseTaiKhoan=new DatabaseTaiKhoan(getContext());
        listTaiKhoan=new ArrayList<>();
        btnAddTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemTaiKhoan();
            }
        });
        LoadTaiKhoan();

        return view;
    }



    private void ThemTaiKhoan() {

        final Dialog add_TaiKhoan_layout=new Dialog(getContext());
        add_TaiKhoan_layout.setTitle("Thêm Loại Thu ");
        add_TaiKhoan_layout.setCancelable(false);

        add_TaiKhoan_layout.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddNew();
                add_TaiKhoan_layout.cancel();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_TaiKhoan_layout.cancel();
            }
        });
        add_TaiKhoan_layout.show();
    }

    private void btnAddNew() {
        taiKhoan=new TaiKhoan(
                edtNameTK.getText().toString(),
                edtSoTienTK.getText().toString()
        );
        long check=databaseTaiKhoan.ThemTaiKhoan(taiKhoan);
        if(check>0){
            Toast.makeText(getContext(), "Thêm Thành Công!!!", Toast.LENGTH_SHORT).show();
            LoadTaiKhoan();
        }
        else {
            Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
        }

    }
    public void LoadTaiKhoan() {
        listTaiKhoan=databaseTaiKhoan.getTaiKhoan();
    }
}
