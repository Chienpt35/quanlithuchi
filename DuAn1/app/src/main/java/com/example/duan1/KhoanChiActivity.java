package com.example.duan1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.DAO.LoaiChiDAO;
import com.example.duan1.Task.MoneyQueryTask;
import com.example.duan1.model.KhoanChi;
import com.example.duan1.model.MoneyLimit;
import com.example.duan1.model.MyAlerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class KhoanChiActivity extends AppCompatActivity {
    private EditText edtTenKhoanChi;
    private Spinner spnKhoanChi;
    private EditText edtSoTienChi;
    private EditText edtNgayChi;
    private EditText edtGhiChuChi;
    private Button btnThemKhoanChi;
    private Button btnDSKhoanChi;
    private Button btnChonNgay;
    private Button btnSuaKhoanChi;
    KhoanChiDAO khoanChiDAO;
    LoaiChiDAO loaiChiDAO;
    String id = "";
    List<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    public SimpleDateFormat simpleDateFormat;
    public Calendar calendar;
    int loaichi;
    MoneyLimit moneyLimit;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoan_chi);
        AnhXa();

        MoneyQueryTask moneyQueryTask = new MoneyQueryTask(this);
        moneyQueryTask.getAllMoneys(new MoneyQueryTask.OnQuery<List<MoneyLimit>>() {
            @Override
            public void onResult(List<MoneyLimit> moneyLimits) {
                for (int i = 0; i < moneyLimits.size(); i++) {
                    moneyLimit = moneyLimits.get(i);
                }
                if (moneyLimit != null){
                    money = moneyLimit.money;
                }
            }
        });
        if (khoanChiDAO.getChi() == null){
            moneyQueryTask.deleteMoneys(moneyLimit);
        }
        btnThemKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaichi = spnKhoanChi.getSelectedItemPosition();
                try {
                    String tenchi = edtTenKhoanChi.getText().toString().trim();
                    String sotien = edtSoTienChi.getText().toString().trim();
                    String ngaychi = edtNgayChi.getText().toString().trim();
                        if (tenchi.isEmpty() && sotien.isEmpty() && ngaychi.isEmpty()) {
                            edtTenKhoanChi.setError("Không được để trống !");
                            edtSoTienChi.setError("Không được để trống !");
                            edtNgayChi.setError("Không được để trống !");
                        } else {
                            if (khoanChiDAO.getChi() > money){
                                MyAlerDialog myAlerDialog = new MyAlerDialog(KhoanChiActivity.this);
                                myAlerDialog.getAlert();
                            }
                            KhoanChi khoanChi = new KhoanChi(null, tenchi, loaichi, Integer.parseInt(sotien), ngaychi,
                                    edtGhiChuChi.getText().toString());
                            if (khoanChiDAO.insertKhoanChi(khoanChi) > 0) {
                                Toast.makeText(KhoanChiActivity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(KhoanChiActivity.this, ListKhoanChiActivity.class));
                            } else {
                                Toast.makeText(KhoanChiActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                } catch (NumberFormatException e) {

                }
            }
        });

        btnDSKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KhoanChiActivity.this, ListKhoanChiActivity.class));
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
                edtNgayChi.setEnabled(true);
            }
        });
        btnSuaKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaichi = spnKhoanChi.getSelectedItemPosition();
                KhoanChi khoanChi = new KhoanChi(id, edtTenKhoanChi.getText().toString(), loaichi,
                        Integer.parseInt(edtSoTienChi.getText().toString()), edtNgayChi.getText().toString(),
                        edtGhiChuChi.getText().toString());
                if (khoanChiDAO.updateKhoanChi(khoanChi) > 0) {
                    Toast.makeText(KhoanChiActivity.this, "Sửa Thành Công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(KhoanChiActivity.this, ListKhoanChiActivity.class));
                } else {
                    Toast.makeText(KhoanChiActivity.this, "Sửa thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void AnhXa() {
        edtTenKhoanChi = (EditText) findViewById(R.id.edtTenKhoanChi);
        spnKhoanChi = (Spinner) findViewById(R.id.spnKhoanChi);
        edtSoTienChi = (EditText) findViewById(R.id.edtSoTienChi);
        edtNgayChi = (EditText) findViewById(R.id.edtNgayChi);
        edtGhiChuChi = (EditText) findViewById(R.id.edtGhiChuChi);
        btnThemKhoanChi = (Button) findViewById(R.id.btnThemKhoanChi);
        btnDSKhoanChi = (Button) findViewById(R.id.btnDSKhoanChi);
        btnChonNgay = (Button) findViewById(R.id.btnChonNgay);
        btnSuaKhoanChi = (Button) findViewById(R.id.btnSuaKhoanChi);
        khoanChiDAO = new KhoanChiDAO(KhoanChiActivity.this);
        loaiChiDAO = new LoaiChiDAO(this);
        arrayList = loaiChiDAO.getTenLoaiChi();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnKhoanChi.setGravity(Gravity.CENTER);
        spnKhoanChi.setPadding(5, 5, 5, 5);
        spnKhoanChi.setAdapter(arrayAdapter);
        edtNgayChi.setEnabled(false);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bun");
            id = bundle.getString("id");
            edtTenKhoanChi.setText(bundle.getString("tenKhoanChi"));
            loaichi = bundle.getInt("loaiChi");
            spnKhoanChi.setSelection(loaichi);
            edtSoTienChi.setText(String.valueOf(bundle.getInt("soTienChi")));
            edtNgayChi.setText(bundle.getString("ngayChi"));
            edtGhiChuChi.setText(bundle.getString("ghiChu"));

        } catch (Exception e) {

        }
    }

    public void date() {
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                edtNgayChi.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                edtNgayChi.setEnabled(false);
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

}
