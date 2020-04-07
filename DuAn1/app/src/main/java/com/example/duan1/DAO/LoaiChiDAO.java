package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1.Database.MyDatabase;
import com.example.duan1.model.LoaiChi;

import java.util.ArrayList;
import java.util.List;


public class LoaiChiDAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public LoaiChiDAO(Context context) {
        myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public static final String SQL_LoaiChi = "CREATE TABLE LoaiChi (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " tenLoaiChi TEXT"+
            ");";
    public static final String TABLE_LoaiChi = "LoaiChi";

    public int insertLoaiChi(LoaiChi loaiChi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",loaiChi.getId());
        contentValues.put("tenLoaiChi",loaiChi.getTenLoaiChi());
        if (db.insert(TABLE_LoaiChi,null,contentValues) > 0 ){
            return 1;
        }
        return -1;
    }
    public List<LoaiChi> getAllLoaiChi(){
        List<LoaiChi> loaiChiList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LoaiChi,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            LoaiChi loaiChi = new LoaiChi();
            loaiChi.setId(cursor.getString(0));
            loaiChi.setTenLoaiChi(cursor.getString(1));
            loaiChiList.add(loaiChi);
            cursor.moveToNext();
        }
        cursor.close();
        return loaiChiList;
    }
    public List<String> getTenLoaiChi(){
        List<String> stringList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LoaiChi,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            stringList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return stringList;
    }
    public long deleteLoaiChi(LoaiChi loaiChi){
        return db.delete(TABLE_LoaiChi,"id=?", new String[]{loaiChi.getId()});
    }
    public int updateLoaiChi(LoaiChi loaiChi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoaiChi",loaiChi.getTenLoaiChi());
        if (db.update(TABLE_LoaiChi,contentValues,"id=?", new String[]{loaiChi.getId()}) > 0 ){
            return 1;
        }
        return -1;
    }
}
