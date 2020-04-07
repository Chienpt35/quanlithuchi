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

    @ColumnInfo(name = "dateBegin")
    public int dateBegin;

    @ColumnInfo(name = "dateFinish")
    public int dateFinish;
}
