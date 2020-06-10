package com.example.develop.appquanlichitieu.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.appquanlichitieu.Database.DatabaseTaiKhoan;
import com.example.develop.appquanlichitieu.Model.TaiKhoan;
import com.example.develop.appquanlichitieu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Develop on 5/6/2018.
 */

class TaiKhoanViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTenTaiKhoan,txtSoTienTaiKhoan;
    public ImageButton btnDel;


    public TaiKhoanViewHolder(View itemView) {
        super(itemView);
        txtTenTaiKhoan=itemView.findViewById(R.id.txtTenTaiKhoan);
        btnDel=itemView.findViewById(R.id.btnDel);
        txtSoTienTaiKhoan=itemView.findViewById(R.id.txtSoTientaikhoan);
    }
}

public class AdapterTaiKhoan extends RecyclerView.Adapter<TaiKhoanViewHolder>{

    private List<TaiKhoan> listdata= new ArrayList<>();
    private Context context;
    AdapterTaiKhoan adapterTaiKhoan=this;
    DatabaseTaiKhoan databaseTaiKhoan;

    public AdapterTaiKhoan(List<TaiKhoan> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public TaiKhoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.item_tai_khoan,parent,false);
        return new TaiKhoanViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TaiKhoanViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        databaseTaiKhoan =new DatabaseTaiKhoan(context);

        holder.txtTenTaiKhoan.setText(listdata.get(position).getTenTaiKhoan());
        holder.txtSoTienTaiKhoan.setText(listdata.get(position).getSoTienTaiKhoan() + " VND");

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItem(position);
            }
        });

    }
    private void DeleteItem(int position) {
        boolean check=databaseTaiKhoan.deteleteItem(String.valueOf(listdata.get(position).getId()));
        if(check){
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
            listdata.clear();
            listdata=databaseTaiKhoan.getTaiKhoan();
            adapterTaiKhoan.notifyDataSetChanged();
        }
        else
            Toast.makeText(context, "Failure!!!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }
}
