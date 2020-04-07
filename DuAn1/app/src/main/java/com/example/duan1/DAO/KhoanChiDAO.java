package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1.Database.MyDatabase;
import com.example.duan1.model.KhoanChi;
import java.util.ArrayList;
import java.util.List;

public class KhoanChiDAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public KhoanChiDAO(Context context) {
        myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }

    public static final String SQL_KhoanChi = "CREATE TABLE KhoanChi (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " tenKhoanChi TEXT, " +
            " loaiChi TEXT, " +
            " soTienChi INTEGER, " +
            " ngayChi TEXT, " +
            " ghiChu TEXT" +
            ");";
    public static final String TABLE_KhoanChi = "KhoanChi";

    public int insertKhoanChi(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", khoanChi.getId());
        contentValues.put("tenKhoanChi", khoanChi.getTenKhoanChi());
        contentValues.put("loaiChi", khoanChi.getLoaiChi());
        contentValues.put("soTienChi", khoanChi.getSoTienChi());
        contentValues.put("ngayChi", khoanChi.getNgayChi());
        contentValues.put("ghiChu", khoanChi.getGhiChu());
        if (db.insert(TABLE_KhoanChi, null, contentValues) > 0) {
            return 1;
        }
        return -1;
    }

    public List<KhoanChi> getAllKhoanChi() {
        List<KhoanChi> khoanChiList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_KhoanChi, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            KhoanChi khoanChi = new KhoanChi();
            khoanChi.setId(cursor.getString(0));
            khoanChi.setTenKhoanChi(cursor.getString(1));
            khoanChi.setLoaiChi(cursor.getInt(2));
            khoanChi.setSoTienChi(cursor.getInt(3));
            khoanChi.setNgayChi(cursor.getString(4));
            khoanChi.setGhiChu(cursor.getString(5));
            khoanChiList.add(khoanChi);
            cursor.moveToNext();
        }
        cursor.close();
        return khoanChiList;
    }

    public long deleteKhoanChi(KhoanChi khoanChi) {
        return db.delete(TABLE_KhoanChi, "id=?", new String[]{khoanChi.getId()});
    }

    public int updateKhoanChi(KhoanChi khoanChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoanChi", khoanChi.getTenKhoanChi());
        contentValues.put("loaiChi", khoanChi.getLoaiChi());
        contentValues.put("soTienChi", khoanChi.getSoTienChi());
        contentValues.put("ngayChi", khoanChi.getNgayChi());
        contentValues.put("ghiChu", khoanChi.getGhiChu());
        if (db.update(TABLE_KhoanChi, contentValues, "id=?", new String[]{khoanChi.getId()}) > 0) {
            return 1;
        }
        return -1;
    }
    public Double getChi(){
        double getChi=0;
        String sql = "select sum(soTienChi) from KhoanChi";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            getChi = cursor.getDouble(0);
            cursor.moveToNext();
        }
        return getChi;
    }
}


