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
import com.example.duan1.model.MoneyResult;
import com.example.duan1.model.MyAlerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class ListKhoanChiActivity extends AppCompatActivity implements MoneyResult {
    private RecyclerView rcView;
    private FloatingActionButton flt, fabMoneyLimit, fabListKhoanChi;
    KhoanChiAdapter khoanChiAdapter;
    List<KhoanChi> list;
    KhoanChiDAO khoanChiDAO;
    Animation moveTren, moveTrai, backTren, backTrai;
    boolean isFab = false;
    MoneyQueryTask moneyQueryTask;
    MoneyLimit moneyLimit;
    Double money = 0.0;
    Double tienchi = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_khoan_chi);

        AnhXa();

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFab == false) {
                    move();
                    isFab = true;
                } else {
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

        moneyQueryTask = new MoneyQueryTask(this);
        moneyQueryTask.getMoneys(this);



    }

    public void AnhXa() {

        khoanChiDAO = new KhoanChiDAO(ListKhoanChiActivity.this);
        list = khoanChiDAO.getAllKhoanChi();
        tienchi = khoanChiDAO.getChi();
        moneyLimit = new MoneyLimit();
        rcView = (RecyclerView) findViewById(R.id.rcView);
        flt = (FloatingActionButton) findViewById(R.id.flt);
        khoanChiAdapter = new KhoanChiAdapter(list, ListKhoanChiActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListKhoanChiActivity.this, LinearLayoutManager.VERTICAL, false);
        rcView.setLayoutManager(linearLayoutManager);
        rcView.setAdapter(khoanChiAdapter);
        fabMoneyLimit = findViewById(R.id.fabMoneyLimit);
        fabListKhoanChi = findViewById(R.id.fabListKhoanChi);
        moveTren = AnimationUtils.loadAnimation(this, R.anim.move_tren);
        moveTrai = AnimationUtils.loadAnimation(this, R.anim.move_trai);
        backTren = AnimationUtils.loadAnimation(this, R.anim.back_tren);
        backTrai = AnimationUtils.loadAnimation(this, R.anim.back_trai);
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

    public void back() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabMoneyLimit.getLayoutParams();
        layoutParams.bottomMargin -= (int) (fabMoneyLimit.getWidth() * 3);
        fabMoneyLimit.setLayoutParams(layoutParams);
        fabMoneyLimit.setAnimation(backTren);

        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fabListKhoanChi.getLayoutParams();
        layoutParams1.rightMargin -= (int) (fabListKhoanChi.getWidth() * 3);
        fabListKhoanChi.setLayoutParams(layoutParams1);
        fabListKhoanChi.setAnimation(backTrai);
    }

    @Override
    public void keyQua(MoneyLimit moneyLimit) {
        try{
            money = moneyLimit.money;
            if (tienchi != null){
                if (tienchi > money) {
                    MyAlerDialog myAlerDialog = new MyAlerDialog(ListKhoanChiActivity.this);
                    myAlerDialog.getAlert();
                    Log.e("tienchi", " " + tienchi);
                    Log.e("tienchi", " " + tienchi);Log.e("money", " " + money);

                }
            }
            else {
                moneyQueryTask.deleteMoneys(moneyLimit);
            }
        }catch (NullPointerException e){

        }
    }


}
