package com.example.duan1;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Task.MoneyQueryTask;
import com.example.duan1.model.MoneyLimit;

import java.util.List;

public class GioiThieuActivity extends AppCompatActivity {
    private TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        text = (TextView) findViewById(R.id.text);
        MoneyQueryTask moneyQueryTask = new MoneyQueryTask(this);
        moneyQueryTask.getAllMoneys(new MoneyQueryTask.OnQuery<List<MoneyLimit>>() {
            @Override
            public void onResult(List<MoneyLimit> moneyLimits) {
                for (int i = 0; i < moneyLimits.size(); i++) {

                    text.setText(String.valueOf(moneyLimits.get(i).money));
                }

            }
        });
    }
}
