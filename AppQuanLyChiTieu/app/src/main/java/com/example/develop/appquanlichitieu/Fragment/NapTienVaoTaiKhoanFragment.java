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
import com.example.develop.appquanlichitieu.ViewHolder.AdapterTaiKhoan;
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
    AdapterTaiKhoan adapter;
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

        add_TaiKhoan_layout.setContentView(R.layout.new_item_tai_khoan);
        add_TaiKhoan_layout.getWindow().setBackgroundDrawableResource(R.color.colorWhite);
        btnAdd=add_TaiKhoan_layout.findViewById(R.id.btnAdd);
        btnHuy=add_TaiKhoan_layout.findViewById(R.id.btnHuy);
        edtNameTK=add_TaiKhoan_layout.findViewById(R.id.edtNameTK);
        edtSoTienTK=add_TaiKhoan_layout.findViewById(R.id.edtSotienTk);
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
        if (!validateUsername() && !validatePassword()) {
            Toast.makeText(getContext(),"Không được bỏ trống",Toast.LENGTH_SHORT).show();
            return;
        }else if (!validateUsername()){
            Toast.makeText(getContext(),"Không được bỏ trống tên tài khoản",Toast.LENGTH_SHORT).show();
            return;
        }else if (!validatePassword()){
            Toast.makeText(getContext(),"Không được bỏ trống số tiền",Toast.LENGTH_SHORT).show();
            return;
        }
        String input = "TenTK: " + edtNameTK.getText().toString();
        input += "\n";
        input += "SoTien: " + edtSoTienTK.getText().toString();
        Toast.makeText(getContext(), input, Toast.LENGTH_SHORT).show();
        /////////////////////////////////////////////////////////
        String str1 = edtNameTK.getText().toString();
        String str2 = edtSoTienTK.getText().toString();
        taiKhoan=new TaiKhoan(
                str1,
                str2
        );
        long check=databaseTaiKhoan.ThemTaiKhoan(taiKhoan);
         if(check>0 && str1.length()> 0 && str2.length() > 0){
            Toast.makeText(getContext(), "Thêm Thành Công!!!", Toast.LENGTH_SHORT).show();
            LoadTaiKhoan();
        }else {
            Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
        }

    }
    public void LoadTaiKhoan() {
//        String strtext = getArguments().getString("edttext");
        listTaiKhoan=databaseTaiKhoan.getTaiKhoan();
        adapter=new AdapterTaiKhoan(listTaiKhoan,getContext());
        recyclerView_TaiKhoan.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private boolean validateUsername() {
        String usernameInput = edtNameTK.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            edtNameTK.setError("Field can't be empty");
            return false;
        } else {
            edtNameTK.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = edtSoTienTK.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            edtSoTienTK.setError("Field can't be empty");
            return false;
        } else {
            edtSoTienTK.setError(null);
            return true;
        }
    }

}
