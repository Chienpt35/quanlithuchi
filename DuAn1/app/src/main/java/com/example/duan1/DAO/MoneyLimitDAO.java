package com.example.duan1.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.example.duan1.model.MoneyLimit;

@Dao
public interface MoneyLimitDAO {
    @Insert
    void insertMoney(MoneyLimit... moneyLimits);
    @Delete
    void deleteMoney(MoneyLimit... moneyLimits);
}



