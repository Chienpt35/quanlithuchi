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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MoneyLimitActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private Button button, button2;
    public SimpleDateFormat simpleDateFormat;
    public Calendar calendar;
    private MoneyQueryTask moneyQueryTask;
    private MoneyLimit moneyLimit;
    private List<MoneyLimit> list = new ArrayList<>();
    int ngay, thang, nam;
    int ngayCheck, thangCheck;
    String check;
    public static final int[] chan = {1,3,5,7,9,11};
    public static final int[] le = {4,6,8,10,12};
    public static int thang2 = 2;
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


        moneyQueryTask.getAllMoneys(new MoneyQueryTask.OnQuery<MoneyLimit>() {
            @Override
            public void onResult(MoneyLimit moneyLimits) {
                    moneyLimit = moneyLimits;
            }
        });
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
                calendar.set(year, month, dayOfMonth);
                editText2.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
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
}
