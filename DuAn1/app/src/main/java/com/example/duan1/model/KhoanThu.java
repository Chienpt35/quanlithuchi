package com.example.duan1.model;

public class KhoanThu {
    String id;
    String tenKhoanThu;
    int loaithu;
    int soTienThu;
    String ngayThu;
    String ghiChu;

    public KhoanThu() {

    }

    public KhoanThu(String id, String tenKhoanThu, int loaithu, int soTienThu, String ngayThu, String ghiChu) {
        this.id = id;
        this.tenKhoanThu = tenKhoanThu;
        this.loaithu = loaithu;
        this.soTienThu = soTienThu;
        this.ngayThu = ngayThu;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }

    public int getLoaithu() {
        return loaithu;
    }

    public void setLoaithu(int loaithu) {
        this.loaithu = loaithu;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public String getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(String ngayThu) {
        this.ngayThu = ngayThu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
