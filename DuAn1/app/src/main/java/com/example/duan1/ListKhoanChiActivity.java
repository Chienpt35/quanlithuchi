package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.Adapter.KhoanChiAdapter;
import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.Database.MoneyDatabase;
import com.example.duan1.model.KhoanChi;
import com.example.duan1.model.MoneyLimit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class ListKhoanChiActivity extends AppCompatActivity {
    private RecyclerView rcView;
    private FloatingActionButton flt, fabMoneyLimit, fabListKhoanChi;
    KhoanChiAdapter khoanChiAdapter;
    List<KhoanChi> list;
    KhoanChiDAO khoanChiDAO;

    boolean isFab = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khoan_chi);
        MoneyDatabase moneyDatabase = Room.databaseBuilder(getApplicationContext(), MoneyDatabase.class, "money.db")
                .allowMainThreadQueries()
                .build();

        AnhXa();

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFab){
                    showFab();
                    isFab = false;
                }else {
                    hideFab();
                    isFab = true;
                }


            }
        });
        fabListKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListKhoanChiActivity.this,KhoanChiActivity.class));
            }
        });
    }
    public void AnhXa(){
        khoanChiDAO = new KhoanChiDAO(this);
        list = khoanChiDAO.getAllKhoanChi();
        rcView = (RecyclerView) findViewById(R.id.rcView);
        flt = (FloatingActionButton) findViewById(R.id.flt);
        khoanChiAdapter = new KhoanChiAdapter(list,ListKhoanChiActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListKhoanChiActivity.this,LinearLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(linearLayoutManager);
        rcView.setAdapter(khoanChiAdapter);
        fabMoneyLimit = findViewById(R.id.fabMoneyLimit);
        fabListKhoanChi = findViewById(R.id.fabListKhoanChi);


    }
    public void showFab(){
        fabListKhoanChi.show();
        fabMoneyLimit.show();
    }
    public void hideFab(){
        fabListKhoanChi.hide();
        fabMoneyLimit.hide();
    }
}
