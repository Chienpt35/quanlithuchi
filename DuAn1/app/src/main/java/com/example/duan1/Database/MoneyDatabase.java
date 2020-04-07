package com.example.duan1.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.duan1.DAO.MoneyLimitDAO;
import com.example.duan1.model.MoneyLimit;

@Database(entities = {MoneyLimit.class}, version = 1)
public abstract class MoneyDatabase extends RoomDatabase {
    public abstract MoneyLimitDAO moneyLimitDAO();
}
