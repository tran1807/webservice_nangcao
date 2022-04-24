package com.example.webservice_nangcao;

import java.io.Serializable;

public class NhanVien implements Serializable {


    private int MaNV;
    private String TenNV;
    private String SDT;
    private int Anh;

    public NhanVien(int maNV, String tenNV, String SDT, int anh) {
        MaNV = maNV;
        TenNV = tenNV;
        this.SDT = SDT;
        Anh = anh;
    }

    public NhanVien(int maNV, String tenNV, String SDT) {
        MaNV = maNV;
        TenNV = tenNV;
        this.SDT = SDT;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getAnh() {
        return Anh;
    }

    public void setAnh(int anh) {
        Anh = anh;
    }
}
