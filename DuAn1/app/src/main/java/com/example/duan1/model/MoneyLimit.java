package com.example.duan1.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoneyLimit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "money")
    public Double money;

    @ColumnInfo(name = "month")
    public String month;

    public MoneyLimit(Double money, String month) {
        this.money = money;
        this.month = month;
    }

    public MoneyLimit() {
    }

    public int getId() {
        return id;
    }

    public Double getMoney() {
        return money;
    }

    public String getMonth() {
        return month;
    }
}
