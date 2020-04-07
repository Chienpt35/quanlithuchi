package com.example.duan1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.KhoanThuDAO;
import com.example.duan1.DAO.LoaiThuDAO;
import com.example.duan1.model.KhoanThu;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class KhoanThuActivity extends AppCompatActivity {
    private EditText edtTenKhoanThu;
    private Spinner spnKhoanThu;
    private EditText edtSoTienThu;
    private EditText edtNgayThu;
    private EditText edtGhiChuThu;
    private Button btnThemKhoanThu;
    private Button btnDSKhoanThu;
    private Button btnChonNgay;
    private Button btnSuaKhoanThu;
    public SimpleDateFormat simpleDateFormat;
    public Calendar calendar;
    KhoanThuDAO khoanThuDAO;
    LoaiThuDAO loaiThuDAO;
    String id= "";
    List<String> stringList;
    ArrayAdapter<String> arrayAdapter1;
    int loaithu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_thu);
        anhXa();
        btnThemKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaithu = spnKhoanThu.getSelectedItemPosition();
                try {
                    String tenthu = edtTenKhoanThu.getText().toString().trim();
                    String sotien = edtSoTienThu.getText().toString().trim();
                    String ngaythu = edtNgayThu.getText().toString().trim();
                    if (tenthu.isEmpty() && sotien.isEmpty() && ngaythu.isEmpty()){
                        edtTenKhoanThu.setError("Không được để trống !");
                        edtSoTienThu.setError("Không được để trống !");
                        edtNgayThu.setError("Không được để trống !");
                    }else {
                        KhoanThu khoanThu = new KhoanThu(null, edtTenKhoanThu.getText().toString(), loaithu,
                                Integer.parseInt(sotien), edtNgayThu.getText().toString(),
                                edtGhiChuThu.getText().toString());
                        if (khoanThuDAO.insertKhoanThu(khoanThu) > 0) {
                            Toast.makeText(KhoanThuActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(KhoanThuActivity.this, ListKhoanThuActivity.class));
                        } else {
                            Toast.makeText(KhoanThuActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (NumberFormatException e){

                }
            }
        });
        btnDSKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KhoanThuActivity.this,ListKhoanThuActivity.class));
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
                edtNgayThu.setEnabled(true);
            }
        });
        btnSuaKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaithu = spnKhoanThu.getSelectedItemPosition();
                KhoanThu khoanThu = new KhoanThu(id,edtTenKhoanThu.getText().toString(), loaithu,
                        Integer.parseInt(edtSoTienThu.getText().toString()),edtNgayThu.getText().toString(),
                        edtGhiChuThu.getText().toString());
                if (khoanThuDAO.updateKhoanthu(khoanThu) > 0){
                    Toast.makeText(KhoanThuActivity.this, "Sửa Thành Công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(KhoanThuActivity.this,ListKhoanThuActivity.class));
                }else {
                    Toast.makeText(KhoanThuActivity.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void date() {
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                edtNgayThu.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                edtNgayThu.setEnabled(false);
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    public void anhXa(){
        edtTenKhoanThu = (EditText) findViewById(R.id.edtTenKhoanThu);
        spnKhoanThu = (Spinner) findViewById(R.id.spnKhoanThu);
        edtSoTienThu = (EditText) findViewById(R.id.edtSoTienThu);
        edtNgayThu = (EditText) findViewById(R.id.edtNgayThu);
        edtGhiChuThu = (EditText) findViewById(R.id.edtGhiChuThu);
        btnThemKhoanThu = (Button) findViewById(R.id.btnThemKhoanThu);
        btnDSKhoanThu = (Button) findViewById(R.id.btnDSKhoanThu);
        btnChonNgay = (Button) findViewById(R.id.btnChonNgay);
        btnSuaKhoanThu = (Button) findViewById(R.id.btnSuaKhoanThu);
        khoanThuDAO = new KhoanThuDAO(this);
        loaiThuDAO = new LoaiThuDAO(this);

        edtNgayThu.setEnabled(false);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");



        stringList = loaiThuDAO.getTenLoaiThu();
        arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,stringList);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnKhoanThu.setPadding(5,5,5,5);
        spnKhoanThu.setAdapter(arrayAdapter1);


        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bun");
            id = bundle.getString("id");
            edtTenKhoanThu.setText(bundle.getString("tenKhoanThu"));
            loaithu = bundle.getInt("loaithu");
            spnKhoanThu.setSelection(loaithu);
            edtSoTienThu.setText(String.valueOf(bundle.getInt("soTienThu")));
            edtNgayThu.setText(bundle.getString("ngayThu"));
            edtGhiChuThu.setText(bundle.getString("ghiChu"));
        }catch (Exception e){

        }

    }
}
