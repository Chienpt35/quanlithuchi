package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.LoaiChiDAO;
import com.example.duan1.model.LoaiChi;

public class LoaiChiActivity extends AppCompatActivity {
    private EditText edtTenChi;
    private Button btnThemLoaiChi;
    private Button btnDSLoaiChi;
    private Button btnSuaLoaiChi;



    String id = "";
    LoaiChiDAO loaiChiDAO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi);
        anhXa();
        btnThemLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenchi = edtTenChi.getText().toString().trim();
                if (tenchi.isEmpty()){
                    edtTenChi.setError("Không được để trống !");
                }else {
                    LoaiChi loaiChi = new LoaiChi(null, edtTenChi.getText().toString());
                    if (loaiChiDAO.insertLoaiChi(loaiChi) > 0) {
                        Toast.makeText(LoaiChiActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoaiChiActivity.this, ListLoaiChiActivity.class));
                    } else {
                        Toast.makeText(LoaiChiActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnDSLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoaiChiActivity.this,ListLoaiChiActivity.class));
            }
        });
        btnSuaLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi loaiChi = new LoaiChi(id,edtTenChi.getText().toString());
                if (loaiChiDAO.updateLoaiChi(loaiChi) > 0){
                    Toast.makeText(LoaiChiActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoaiChiActivity.this,ListLoaiChiActivity.class));
                }else {
                    Toast.makeText(LoaiChiActivity.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void anhXa(){
        edtTenChi = (EditText) findViewById(R.id.edtTenChi);
        btnThemLoaiChi = (Button) findViewById(R.id.btnThemLoaiChi);
        btnDSLoaiChi = (Button) findViewById(R.id.btnDSLoaiChi);
        loaiChiDAO = new LoaiChiDAO(this);
        btnSuaLoaiChi = (Button) findViewById(R.id.btnSuaLoaiChi);

        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bun");
            id = bundle.getString("id");
            edtTenChi.setText(bundle.getString("tenLoaiChi"));
        }catch (Exception e){

        }
    }
}
