package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.LoaiThuDAO;
import com.example.duan1.model.LoaiChi;
import com.example.duan1.model.LoaiThu;

public class LoaiThuActivity extends AppCompatActivity {
    private EditText edtTenThu;
    private Button btnThemLoaiThu;
    private Button btnDSLoaiThu;
    private Button btnSuaLoaiThu;



    String  id = "";
    LoaiThuDAO loaiThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_thu);
        anhXa();
        btnThemLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu loaiThu = new LoaiThu(null,edtTenThu.getText().toString());
                if (loaiThuDAO.insertLoaiThu(loaiThu) > 0){
                    Toast.makeText(LoaiThuActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoaiThuActivity.this,ListLoaiThuActivity.class));
                }else {
                    Toast.makeText(LoaiThuActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDSLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoaiThuActivity.this,ListLoaiThuActivity.class));
            }
        });
        btnSuaLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu loaiThu = new LoaiThu(id,edtTenThu.getText().toString());
                if (loaiThuDAO.updateLoaiThu(loaiThu) > 0){
                    Toast.makeText(LoaiThuActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoaiThuActivity.this,ListLoaiThuActivity.class));
                }else {
                    Toast.makeText(LoaiThuActivity.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void anhXa(){
        edtTenThu = (EditText) findViewById(R.id.edtTenThu);
        btnThemLoaiThu = (Button) findViewById(R.id.btnThemLoaiThu);
        btnDSLoaiThu = (Button) findViewById(R.id.btnDSLoaiThu);
        loaiThuDAO = new LoaiThuDAO(this);
        btnSuaLoaiThu = (Button) findViewById(R.id.btnSuaLoaiThu);

        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bun");
            id = bundle.getString("id");
            edtTenThu.setText(bundle.getString("tenLoaiThu"));
        }catch (Exception e){

        }

    }
}
