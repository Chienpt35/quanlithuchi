package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.duan1.Adapter.KhoanChiAdapter;
import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.Database.MoneyDatabase;
import com.example.duan1.Task.MoneyQueryTask;
import com.example.duan1.model.KhoanChi;
import com.example.duan1.model.MoneyLimit;
import com.example.duan1.model.MyAlerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class ListKhoanChiActivity extends AppCompatActivity {
    private RecyclerView rcView;
    private FloatingActionButton flt, fabMoneyLimit, fabListKhoanChi;
    KhoanChiAdapter khoanChiAdapter;
    List<KhoanChi> list;
    KhoanChiDAO khoanChiDAO;
    Animation moveTren, moveTrai, backTren, backTrai;
    boolean isFab = false;
    MoneyQueryTask moneyQueryTask;
    MoneyLimit moneyLimit;
    int money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khoan_chi);



        AnhXa();

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFab == false){
                    move();
                    isFab = true;
                }else {
                    back();
                    isFab = false;
                }

            }
        });
        fabListKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListKhoanChiActivity.this, KhoanChiActivity.class));
            }
        });
        fabMoneyLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListKhoanChiActivity.this, MoneyLimitActivity.class));
            }
        });

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
        else if (khoanChiDAO.getChi() > money) {
            MyAlerDialog myAlerDialog = new MyAlerDialog(this);
            myAlerDialog.getAlert();
        }
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
        moveTren = AnimationUtils.loadAnimation(this, R.anim.move_tren);
        moveTrai = AnimationUtils.loadAnimation(this, R.anim.move_trai);
        backTren = AnimationUtils.loadAnimation(this, R.anim.back_tren);
        backTrai = AnimationUtils.loadAnimation(this, R.anim.back_trai);
        moneyQueryTask = new MoneyQueryTask(this);
    }

    public void move() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabMoneyLimit.getLayoutParams();
        layoutParams.bottomMargin = (int) (fabMoneyLimit.getWidth() * 3);
        fabMoneyLimit.setLayoutParams(layoutParams);
        fabMoneyLimit.setAnimation(moveTren);

        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fabListKhoanChi.getLayoutParams();
        layoutParams1.rightMargin = (int) (fabListKhoanChi.getWidth() * 3);
        fabListKhoanChi.setLayoutParams(layoutParams1);
        fabListKhoanChi.setAnimation(moveTrai);
    }
    public void back(){
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabMoneyLimit.getLayoutParams();
        layoutParams.bottomMargin -= (int) (fabMoneyLimit.getWidth() * 3);
        fabMoneyLimit.setLayoutParams(layoutParams);
        fabMoneyLimit.setAnimation(backTren);

        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fabListKhoanChi.getLayoutParams();
        layoutParams1.rightMargin -= (int) (fabListKhoanChi.getWidth() * 3);
        fabListKhoanChi.setLayoutParams(layoutParams1);
        fabListKhoanChi.setAnimation(backTrai);
    }
}
