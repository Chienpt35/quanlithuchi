package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.KhoanThuAdapter;
import com.example.duan1.DAO.KhoanThuDAO;
import com.example.duan1.model.KhoanThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListKhoanThuActivity extends AppCompatActivity {
    private RecyclerView rcView;
    private FloatingActionButton flt;
    KhoanThuAdapter khoanThuAdapter;
    List<KhoanThu> list;
    KhoanThuDAO khoanThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khoan_thu);
        AnhXa();
        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListKhoanThuActivity.this,KhoanThuActivity.class));
            }
        });
    }
    public void AnhXa(){
        khoanThuDAO = new KhoanThuDAO(this);
        list = khoanThuDAO.getAllKhoanThu();
        rcView = (RecyclerView) findViewById(R.id.rcView);
        flt = (FloatingActionButton) findViewById(R.id.flt);
        khoanThuAdapter = new KhoanThuAdapter(ListKhoanThuActivity.this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListKhoanThuActivity.this,LinearLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(linearLayoutManager);
        rcView.setAdapter(khoanThuAdapter);
    }
}
