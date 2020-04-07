package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1.Database.MyDatabase;
import com.example.duan1.model.LoaiThu;
import java.util.ArrayList;
import java.util.List;


public class LoaiThuDAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public LoaiThuDAO(Context context) {
        myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public static final String SQL_LoaiThu = "CREATE TABLE LoaiThu (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " tenLoaiThu TEXT"+
            ");";
    public static final String TABLE_LoaiThu = "LoaiThu";

    public int insertLoaiThu(LoaiThu loaiThu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",loaiThu.getId());
        contentValues.put("tenLoaiThu",loaiThu.getTenLoaiThu());
        if (db.insert(TABLE_LoaiThu,null,contentValues) > 0 ){
            return 1;
        }
        return -1;
    }
    public List<LoaiThu> getAllLoaiThu(){
        List<LoaiThu> loaiThuList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LoaiThu,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            LoaiThu loaiThu = new LoaiThu();
            loaiThu.setId(cursor.getString(0));
            loaiThu.setTenLoaiThu(cursor.getString(1));
            loaiThuList.add(loaiThu);
            cursor.moveToNext();
        }
        cursor.close();
        return loaiThuList;
    }
    public List<String> getTenLoaiThu(){
        List<String> stringList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LoaiThu,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            stringList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return stringList;
    }
    public long deleteLoaiThu(LoaiThu loaiThu){
        return db.delete(TABLE_LoaiThu,"id=?", new String[]{loaiThu.getId()});
    }
    public int updateLoaiThu(LoaiThu loaiThu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoaiThu",loaiThu.getTenLoaiThu());
        if (db.update(TABLE_LoaiThu,contentValues,"id=?",new String[]{loaiThu.getId()}) > 0 ){
            return 1;
        }
        return -1;
    }


}
