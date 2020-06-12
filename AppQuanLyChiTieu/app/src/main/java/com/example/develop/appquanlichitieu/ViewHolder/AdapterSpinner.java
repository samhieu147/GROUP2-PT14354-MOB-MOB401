package com.example.develop.appquanlichitieu.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.develop.appquanlichitieu.Model.LoaiThu;
import com.example.develop.appquanlichitieu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Develop on 5/5/2018.
 */


public class AdapterSpinner extends ArrayAdapter<LoaiThu> {

    public List<LoaiThu> listData= new ArrayList<>();
    public Context context;
    public int resource;


    public AdapterSpinner(@NonNull Context context, int resource, @NonNull List<LoaiThu> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listData=objects;
    }
    class viewHolder{
        TextView txtLoaiThu;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder view;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_spinner_loaithu,parent,false);
            view=new viewHolder();

            view.txtLoaiThu=convertView.findViewById(R.id.txtLoaiThu);
            convertView.setTag(view);
        }
        else
        {
            view= (viewHolder) convertView.getTag();
        }
                view.txtLoaiThu.setText(listData.get(position).getTenLoaiThu());



        return convertView;
    }
}
