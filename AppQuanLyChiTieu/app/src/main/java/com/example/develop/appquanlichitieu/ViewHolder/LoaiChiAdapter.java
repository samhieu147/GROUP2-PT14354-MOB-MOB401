package com.example.develop.appquanlichitieu.ViewHolder;



import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.develop.appquanlichitieu.Database.DatabaseLoaiChi;
import com.example.develop.appquanlichitieu.Model.LoaiChi;
import com.example.develop.appquanlichitieu.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;

class LoaiChiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

public TextView txt_LoaiKhoangThu;
public ImageButton btnDel,btnEdit;


public LoaiChiViewHolder(View itemView) {
        super(itemView);

        txt_LoaiKhoangThu=itemView.findViewById(R.id.txtLoaiThu);
        btnDel=itemView.findViewById(R.id.btnDel);
        btnEdit=itemView.findViewById(R.id.btnEdit);
        }

@Override
public void onClick(View view) {

        }
        }

public class LoaiChiAdapter  extends RecyclerView.Adapter<LoaiChiViewHolder>{

    private List<LoaiChi> listData= new ArrayList<>();
    private Context context;
    LoaiChiAdapter loaiChiAdapter=this;

    DatabaseLoaiChi databaseLoaiChi;


    public LoaiChiAdapter(List<LoaiChi> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public LoaiChiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.item_loai_thu,parent,false);
        return new LoaiChiViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LoaiChiViewHolder holder, final int position) {
        databaseLoaiChi=new DatabaseLoaiChi(context);

        holder.txt_LoaiKhoangThu.setText(listData.get(position).getTenLoaiChi());

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItem(position);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog(position);
            }
        });
    }

    private void ShowDialog(int position) {
        final LoaiChi loaiChi=listData.get(position);
        final MaterialEditText editName;
        FButton btnEdit,btnHuy;
        final Dialog add_edit_layout=new Dialog(context);
        add_edit_layout.setTitle("Thêm Loại Thu ");
        add_edit_layout.setCancelable(false);

        add_edit_layout.setContentView(R.layout.edit_item);
        add_edit_layout.getWindow().setBackgroundDrawableResource(R.color.colorWhite);

        btnEdit=add_edit_layout.findViewById(R.id.btnEdit);
        btnHuy=add_edit_layout.findViewById(R.id.btnCancel);
        editName=add_edit_layout.findViewById(R.id.edtNameEdit);
        editName.setText(loaiChi.getTenLoaiChi());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loaiChi.setTenLoaiChi(editName.getText().toString());
                boolean check=databaseLoaiChi.UpdateLoaiChi(loaiChi);
                if(check)
                {
                    Toast.makeText(context, "Item Edited", Toast.LENGTH_SHORT).show();
                    listData.clear();
                    listData=databaseLoaiChi.getLoaiChi();
                    loaiChiAdapter.notifyDataSetChanged();
                }else
                    Toast.makeText(context, "Failure!!!", Toast.LENGTH_SHORT).show();
                add_edit_layout.cancel();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_edit_layout.cancel();
            }
        });
        add_edit_layout.show();
    }

    private void DeleteItem(int position) {
        boolean check=databaseLoaiChi.deteleteItemLoaiChi(String.valueOf(listData.get(position).getIdloaiChi()));
        if(check){
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
            listData.clear();
            listData=databaseLoaiChi.getLoaiChi();
            loaiChiAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(context, "Failure!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}