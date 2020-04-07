package com.example.duan1.model;

public class KhoanChi {
    String id;
    String tenKhoanChi;
    int loaiChi;
    int soTienChi;
    String ngayChi;
    String ghiChu;

    public KhoanChi() {
    }

    public KhoanChi(String id, String tenKhoanChi, int loaiChi, int soTienChi, String ngayChi, String ghiChu) {
        this.id = id;
        this.tenKhoanChi = tenKhoanChi;
        this.loaiChi = loaiChi;
        this.soTienChi = soTienChi;
        this.ngayChi = ngayChi;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }

    public int getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(int loaiChi) {
        this.loaiChi = loaiChi;
    }

    public int getSoTienChi() {
        return soTienChi;
    }

    public void setSoTienChi(int soTienChi) {
        this.soTienChi = soTienChi;
    }

    public String getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(String ngayChi) {
        this.ngayChi = ngayChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
