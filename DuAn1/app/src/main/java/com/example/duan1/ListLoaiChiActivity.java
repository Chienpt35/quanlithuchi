package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1.Adapter.LoaiChiAdapter;
import com.example.duan1.Adapter.LoaiThuAdapter;
import com.example.duan1.DAO.LoaiChiDAO;
import com.example.duan1.DAO.LoaiThuDAO;
import com.example.duan1.model.LoaiChi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListLoaiChiActivity extends AppCompatActivity {
    private RecyclerView rcView;
    private FloatingActionButton flt;
    LoaiChiDAO loaiChiDAO;
    List<LoaiChi> list;
    LoaiChiAdapter loaiChiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loai_chi);
        anhXa();
        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListLoaiChiActivity.this,LoaiChiActivity.class));
            }
        });
    }
    public void anhXa(){
        rcView = (RecyclerView) findViewById(R.id.rcView);
        flt = (FloatingActionButton) findViewById(R.id.flt);
        loaiChiDAO = new LoaiChiDAO(this);
        list = loaiChiDAO.getAllLoaiChi();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListLoaiChiActivity.this,LinearLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(linearLayoutManager);
        loaiChiAdapter = new LoaiChiAdapter(this,list);
        rcView.setAdapter(loaiChiAdapter);
    }
}
