package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1.Database.MyDatabase;
import com.example.duan1.model.KhoanThu;
import java.util.ArrayList;
import java.util.List;


public class KhoanThuDAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public KhoanThuDAO(Context context) {
        myDatabase = new MyDatabase(context);
        db = myDatabase.getWritableDatabase();
    }
    public static final String SQL_KhoanThu = "CREATE TABLE KhoanThu (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " tenKhoanThu TEXT, "+
            " loaithu INTEGER, "+
            " soTienThu INTEGER, "+
            " ngayThu TEXT, "+
            " ghiChu TEXT"+
            ");";
    public static final String TABLE_KhoanThu = "KhoanThu";

    public int insertKhoanThu(KhoanThu khoanThu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",khoanThu.getId());
        contentValues.put("tenKhoanThu",khoanThu.getTenKhoanThu());
        contentValues.put("loaithu",khoanThu.getLoaithu());
        contentValues.put("soTienThu",khoanThu.getSoTienThu());
        contentValues.put("ngayThu",khoanThu.getNgayThu());
        contentValues.put("ghiChu",khoanThu.getGhiChu());
        try {
            if (db.insert(TABLE_KhoanThu,null,contentValues) < 0 ){
                return -1;
            }
        }catch (Exception ex){

        }

        return 1;
    }
    public List<KhoanThu> getAllKhoanThu(){
        List<KhoanThu> khoanThuList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_KhoanThu,null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            KhoanThu khoanThu = new KhoanThu();
            khoanThu.setId(cursor.getString(0));
            khoanThu.setTenKhoanThu(cursor.getString(1));
            khoanThu.setLoaithu(cursor.getInt(2));
            khoanThu.setSoTienThu(cursor.getInt(3));
            khoanThu.setNgayThu(cursor.getString(4));
            khoanThu.setGhiChu(cursor.getString(5));
            khoanThuList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();
        return khoanThuList;
    }
    public long deleteKhoanThu(KhoanThu khoanThu){
        return db.delete(TABLE_KhoanThu,"id=?",new String[]{khoanThu.getId()});
    }
    public int updateKhoanthu(KhoanThu khoanThu){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoanThu",khoanThu.getTenKhoanThu());
        contentValues.put("loaithu",khoanThu.getLoaithu());
        contentValues.put("soTienThu",khoanThu.getSoTienThu());
        contentValues.put("ngayThu",khoanThu.getNgayThu());
        contentValues.put("ghiChu",khoanThu.getGhiChu());
        try {
            if (db.update(TABLE_KhoanThu,contentValues,"id=?", new String[]{khoanThu.getId()}) < 0 ){
                return -1;
            }
        }catch (Exception ex){

        }
        return 1;
    }
    public Double getThu(){
        double getThu=0;
        String sql = "select sum(soTienThu) from KhoanThu";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            getThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        return getThu;
    }


}
