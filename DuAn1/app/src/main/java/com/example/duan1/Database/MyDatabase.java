package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.DAO.KhoanThuDAO;
import com.example.duan1.DAO.LoaiChiDAO;
import com.example.duan1.DAO.LoaiThuDAO;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context) {
        super(context, "ThuChi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KhoanThuDAO.SQL_KhoanThu);
        db.execSQL(KhoanChiDAO.SQL_KhoanChi);
        db.execSQL(LoaiChiDAO.SQL_LoaiChi);
        db.execSQL(LoaiThuDAO.SQL_LoaiThu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(KhoanChiDAO.TABLE_KhoanChi);
        db.execSQL(KhoanThuDAO.TABLE_KhoanThu);
        db.execSQL(LoaiThuDAO.TABLE_LoaiThu);
        db.execSQL(LoaiChiDAO.TABLE_LoaiChi);
    }
}
