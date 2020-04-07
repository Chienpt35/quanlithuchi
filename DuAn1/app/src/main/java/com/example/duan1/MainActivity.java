package com.example.duan1;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //thêm hình button cho actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_24dp);

        drawer = findViewById(R.id.drawer_layout);




        //xét sự kiện khi ấn vào item menu
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        //phát sáng item khi chọn
                        //menuItem.setChecked(true);


                        //bắt sự kiện chọn 1 item
                        switch (menuItem.getItemId()) {
                            case R.id.nav_loaithu:
                                startActivity(new Intent(MainActivity.this,ListLoaiThuActivity.class));
                                break;
                            case R.id.nav_khoanthu:
                               startActivity(new Intent(MainActivity.this,ListKhoanThuActivity.class));
                                break;
                            case R.id.nav_loaichi:
                                startActivity(new Intent(MainActivity.this,ListLoaiChiActivity.class));
                                break;
                            case R.id.nav_khoanchi:
                                startActivity(new Intent(MainActivity.this,ListKhoanChiActivity.class));
                                break;
                            case R.id.nav_thongke:
                                startActivity(new Intent(MainActivity.this,ThongKeActivity.class));
                                break;
                            case R.id.nav_gioithieu:
                                startActivity(new Intent(MainActivity.this,GioiThieuActivity.class));
                                break;
                            case R.id.nav_thoat:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                LayoutInflater inflater = getLayoutInflater();
                                builder.setView(inflater.inflate(R.layout.alert_exit,null));
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                });
                                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.show();
                                break;
                        }

                        //đóng navigation khi chọn 1 item
                        drawer.closeDrawers();

                        return true;
                    }
                }
        );


    }


    //xét sự kiện ấn vào hình button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loaichi(View view) {
        startActivity(new Intent(MainActivity.this,ListLoaiChiActivity.class));
    }

    public void loaithu(View view) {
        startActivity(new Intent(MainActivity.this,ListLoaiThuActivity.class));
    }

    public void khoanchi(View view) {
        startActivity(new Intent(MainActivity.this,ListKhoanChiActivity.class));
    }

    public void khoanthu(View view) {
        startActivity(new Intent(MainActivity.this,ListKhoanThuActivity.class));
    }

    public void gioithieu(View view) {
        startActivity(new Intent(MainActivity.this,GioiThieuActivity.class));
    }

    public void thongke(View view) {
        startActivity(new Intent(MainActivity.this,ThongKeActivity.class));
    }
}

