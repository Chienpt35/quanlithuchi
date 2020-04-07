package com.example.duan1.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.duan1.model.MoneyLimit;

import java.util.List;

@Dao
public interface MoneyLimitDAO {
    @Query("SELECT * FROM moneylimit")
    List<MoneyLimit> getListMoney();
    @Insert
    long[] insertMoney(MoneyLimit... moneyLimits);
    @Delete
    void deleteMoney(MoneyLimit... moneyLimits);
}



