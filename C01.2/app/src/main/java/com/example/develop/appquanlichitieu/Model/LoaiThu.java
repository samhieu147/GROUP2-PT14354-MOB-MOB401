package com.example.develop.appquanlichitieu.Model;

/**
 * Created by Develop on 5/4/2018.
 */

public class LoaiThu {

    int idLoaiThu;
    String TenLoaiThu;

    public LoaiThu(int idLoaiThu, String tenLoaiThu) {
        this.idLoaiThu = idLoaiThu;
        TenLoaiThu = tenLoaiThu;
    }
    public LoaiThu(String tenLoaiThu) {
        this.idLoaiThu = idLoaiThu;
        TenLoaiThu = tenLoaiThu;
    }


    public LoaiThu() {
    }

    public int getIdLoaiThu() {
        return idLoaiThu;
    }

    public void setIdLoaiThu(int idLoaiThu) {
        this.idLoaiThu = idLoaiThu;
    }

    public String getTenLoaiThu() {
        return TenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        TenLoaiThu = tenLoaiThu;
    }

    @Override
    public String toString() {
        return TenLoaiThu;
    }
}
