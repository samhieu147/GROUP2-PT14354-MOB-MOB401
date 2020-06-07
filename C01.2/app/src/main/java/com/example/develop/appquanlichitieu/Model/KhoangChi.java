package com.example.develop.appquanlichitieu.Model;

/**
 * Created by Develop on 5/4/2018.
 */

public class KhoangChi {
    int Id;
    String NgayChi,TaiKhoanChi,SoTienChi,MoTaChi,LoaiChi;

    public KhoangChi() {
    }

    public KhoangChi(String ngayChi, String taiKhoanChi, String soTienChi, String moTaChi, String loaiChi) {
        NgayChi = ngayChi;
        TaiKhoanChi = taiKhoanChi;
        SoTienChi = soTienChi;
        MoTaChi = moTaChi;
        LoaiChi = loaiChi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(String ngayChi) {
        NgayChi = ngayChi;
    }

    public String getTaiKhoanChi() {
        return TaiKhoanChi;
    }

    public void setTaiKhoanChi(String taiKhoanChi) {
        TaiKhoanChi = taiKhoanChi;
    }

    public String getSoTienChi() {
        return SoTienChi;
    }

    public void setSoTienChi(String soTienChi) {
        SoTienChi = soTienChi;
    }

    public String getMoTaChi() {
        return MoTaChi;
    }

    public void setMoTaChi(String moTaChi) {
        MoTaChi = moTaChi;
    }

    public String getLoaiChi() {
        return LoaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        LoaiChi = loaiChi;
    }

}
