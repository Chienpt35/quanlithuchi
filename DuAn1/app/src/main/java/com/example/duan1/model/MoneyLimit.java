package com.example.duan1.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoneyLimit {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "money")
    public int money;

    @ColumnInfo(name = "month")
    public String month;

    public MoneyLimit(int id, int money, String month) {
        this.id = id;
        this.money = money;
        this.month = month;
    }

    public MoneyLimit() {
    }
}
