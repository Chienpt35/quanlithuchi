package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.Database.MoneyDatabase;
import com.example.duan1.Task.MoneyQueryTask;
import com.example.duan1.model.MoneyLimit;
import com.example.duan1.model.MoneyResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MoneyLimitActivity extends AppCompatActivity implements MoneyResult {
    private EditText editText;
    private EditText editText2;
    private Button button, button2;
    public SimpleDateFormat simpleDateFormat;
    public Calendar calendar;
    private MoneyQueryTask moneyQueryTask;
    private MoneyLimit moneyLimit;
    private List<MoneyLimit> list = new ArrayList<>();
    int ngay, thang, nam;
    String check, ngayCheck, thangCheck;
    public static final String[] le = {"01","03","05","07","09","11"};
    public static final String[] chan = {"04","06","08","10","12"};
    public static String thang2 = "02";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_limit);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button2 = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        moneyQueryTask = new MoneyQueryTask(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMoney();
            }
        });


        moneyQueryTask.getMoneys(this);
    }

    public void insertMoney() {
        String money = editText.getText().toString().trim();
        String month = editText2.getText().toString().trim();
        Log.e("money", money);
        Log.e("month", month);
        if (money.isEmpty() && month.isEmpty()) {
            editText.setError("Không được để trống !!!");
            editText2.setError("Không được để trống !!!");
        } else {
            moneyQueryTask.deleteMoneys(moneyLimit);
            moneyLimit = new MoneyLimit(castDouble(money), month);
            moneyQueryTask.insertMoneys(new MoneyQueryTask.OnQuery<long[]>() {
                @Override
                public void onResult(long[] longs) {
                    if (longs.length > 0) {
                        Toast.makeText(MoneyLimitActivity.this, "Successfuly !!! ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MoneyLimitActivity.this, ListKhoanChiActivity.class));
                    } else {
                        Toast.makeText(MoneyLimitActivity.this, "Error !!! ", Toast.LENGTH_LONG).show();
                    }
                }
            }, moneyLimit);
        }
    }

    public void date() {
        calendar = Calendar.getInstance();
        ngay = calendar.get(Calendar.DATE);
        thang = calendar.get(Calendar.MONTH);
        nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                if (thang == month){
                    calendar.set(year, month, dayOfMonth);
                    editText2.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    button2.setEnabled(true);
                }else {
                    button2.setEnabled(false);
                    Toast.makeText(MoneyLimitActivity.this, "Bạn đã chọn sai tháng", Toast.LENGTH_SHORT).show();
                }

            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public Double castDouble(String a) {
        Double b = 0.0;
        try {
            b = Double.parseDouble(a);
        } catch (NumberFormatException e) {
            Log.e("NumberFormatException: ", e.toString());
        }
        return b;
    }

    @Override
    public void keyQua(MoneyLimit moneyLimit) {
        check = moneyLimit.month;
        thangCheck = check.substring(3,5);
        String ngay = check.substring(0,2);
        if (thangCheck.equals(chan)){
            ngayCheck = "30";
        }
        else if (thangCheck.equals(thang2)){
            ngayCheck = "29";
        }
        else if (thangCheck.equals(le)){
            ngayCheck = "31";
        }
        if (ngay.equals(ngayCheck)){
            moneyQueryTask.deleteMoneys(moneyLimit);
        }
    }
}
