package com.example.duan1;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.DAO.KhoanThuDAO;

public class ThongKeActivity extends AppCompatActivity {
    private TextView tvtTongThu;
    private TextView tvtTongChi;
    private TextView tvtConLai;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvtTongThu = (TextView) findViewById(R.id.tvtTongThu);
        tvtTongChi = (TextView) findViewById(R.id.tvtTongChi);
        tvtConLai = (TextView) findViewById(R.id.tvtConLai);
        khoanChiDAO = new KhoanChiDAO(this);
        khoanThuDAO = new KhoanThuDAO(this);
        double tongchi = khoanChiDAO.getChi() ;
        tvtTongChi.setText("Tổng Chi: "+tongchi);
        tvtTongThu.setText("Tổng Thu: "+khoanThuDAO.getThu());
        tvtConLai.setText("Còn Lại: " + (khoanThuDAO.getThu()-tongchi ));
    }
}
