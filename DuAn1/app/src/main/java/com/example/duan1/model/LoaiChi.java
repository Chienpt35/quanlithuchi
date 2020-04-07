package com.example.duan1.model;

public class LoaiChi {
    String id;
    String tenLoaiChi;

    public LoaiChi() {

    }

    public LoaiChi(String id, String tenLoaiChi) {
        this.id = id;
        this.tenLoaiChi = tenLoaiChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }
}
