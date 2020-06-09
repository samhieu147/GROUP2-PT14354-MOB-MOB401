package com.example.develop.appquanlichitieu.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.develop.appquanlichitieu.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Develop on 5/6/2018.
 */

public class DatabaseTaiKhoan {

    SQLiteDatabase database;
    String TB="TAB";
    public DatabaseTaiKhoan(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.Open();
    }

    public long ThemTaiKhoan(TaiKhoan taiKhoan){

        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_TAIKHOAN_NAME,taiKhoan.getTenTaiKhoan());

        contentValues.put(CreateDatabase.TB_TAIKHOAN_SOTIEN,taiKhoan.getSoTienTaiKhoan());


        long check=database.insert(CreateDatabase.TB_TAIKHOAN,null,contentValues);

        return check;
    }

    public List<TaiKhoan> getTaiKhoan(){

        List<TaiKhoan> list=new ArrayList<>();

        String TruyVan=" SELECT * FROM " + CreateDatabase.TB_TAIKHOAN;

        Cursor cursor=database.rawQuery(TruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TaiKhoan taiKhoan=new TaiKhoan();
            taiKhoan.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_ID)));
            taiKhoan.setTenTaiKhoan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_NAME)));
            taiKhoan.setSoTienTaiKhoan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TAIKHOAN_SOTIEN)));
            list.add(taiKhoan);
            cursor.moveToNext();
        }
        //Log.d("checkNao", String.valueOf(list));
        return list;
    }

    public boolean UpdateLoaiThu(TaiKhoan taiKhoan){

        ContentValues contentValues=new ContentValues();

        contentValues.put(CreateDatabase.TB_TAIKHOAN_SOTIEN,taiKhoan.getSoTienTaiKhoan());

        long check=database.update(CreateDatabase.TB_TAIKHOAN,contentValues,CreateDatabase.TB_LOAITHU_ID + " = " +taiKhoan.getId(),null);

        if(check !=0){
            return true;
        }else {
            return false;
        }

    }
}
