package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.LoaiThuAdapter;
import com.example.duan1.DAO.LoaiThuDAO;
import com.example.duan1.model.LoaiThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListLoaiThuActivity extends AppCompatActivity {
    public RecyclerView rcView;
    public FloatingActionButton floatLoaiThu;
    LoaiThuAdapter loaiThuAdapter;
    List<LoaiThu> list;
    LoaiThuDAO loaiThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loai_thu);
        anhXa();
        floatLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListLoaiThuActivity.this,LoaiThuActivity.class));
            }
        });
    }
    public void anhXa(){
        rcView = (RecyclerView) findViewById(R.id.rcView);
        floatLoaiThu = (FloatingActionButton) findViewById(R.id.floatLoaiThu);
        loaiThuDAO = new LoaiThuDAO(this);
        list = loaiThuDAO.getAllLoaiThu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListLoaiThuActivity.this,LinearLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(linearLayoutManager);
        loaiThuAdapter = new LoaiThuAdapter(this,list);
        rcView.setAdapter(loaiThuAdapter);
    }
}
