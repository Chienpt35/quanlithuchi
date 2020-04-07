package com.example.duan1.model;

public class LoaiThu {
    String id;
    String tenLoaiThu;

    public LoaiThu() {

    }

    public LoaiThu(String id, String tenLoaiThu) {
        this.id = id;
        this.tenLoaiThu = tenLoaiThu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }
}
