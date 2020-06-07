package com.example.develop.appquanlichitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.develop.appquanlichitieu.Model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Develop on 5/4/2018.
 */

public class DatabaseLoaiThu  {


    SQLiteDatabase database;
    String TB="TAB";
    public DatabaseLoaiThu(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.Open();
    }

    public long AddItem(LoaiThu loaiThu)
    {
        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_LOAITHU_NAMELOAITHU,loaiThu.getTenLoaiThu());
        long check=database.insert(CreateDatabase.TB_LOAITHU,null,contentValues);
        Log.d(TB, String.valueOf(check));
        return check;
    }
    public List<LoaiThu> getLoaiThu(){

        List<LoaiThu> list=new ArrayList<>();

        String TruyVan=" SELECT * FROM " + CreateDatabase.TB_LOAITHU;

        Cursor cursor=database.rawQuery(TruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiThu loaiThu=new LoaiThu();
            loaiThu.setIdLoaiThu(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAITHU_ID)));
            loaiThu.setTenLoaiThu(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAITHU_NAMELOAITHU)));
            list.add(loaiThu);
            cursor.moveToNext();
        }
        Log.d("checkNao", String.valueOf(list));
        return list;
    }
    public boolean UpdateLoaiThu(LoaiThu loaiThu){

        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAITHU_NAMELOAITHU,loaiThu.getTenLoaiThu());
        long check=database.update(CreateDatabase.TB_LOAITHU,contentValues,CreateDatabase.TB_LOAITHU_ID + " = " +loaiThu.getIdLoaiThu(),null);
        if(check !=0){
            return true;
        }else {
            return false;
        }

    }
    public boolean deteleteItem(String id){

        long check= database.delete(CreateDatabase.TB_LOAITHU,CreateDatabase.TB_LOAITHU_ID+"=?",new String[]{String.valueOf(id)});
        if (check !=0){
            return true;
        }
        else {
            return false;
        }
    }
}
