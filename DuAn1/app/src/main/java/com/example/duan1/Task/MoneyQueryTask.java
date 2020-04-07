package com.example.duan1.Task;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.duan1.Database.MoneyDatabase;
import com.example.duan1.model.MoneyLimit;
import com.example.duan1.model.MoneyResult;

import java.util.ArrayList;
import java.util.List;

public class MoneyQueryTask {

    public MoneyDatabase moneyDatabase;



    public MoneyQueryTask(Context context) {
        moneyDatabase = Room.databaseBuilder(context,
                MoneyDatabase.class, "moneylimit.db").build();
        // câu lệnh allowMainThreadQueries bị loại bỏ
    }
    public interface OnQuery<T> {
        void onResult(T t);
    }
    public void getAllMoneys(OnQuery<MoneyLimit> onQuery) {
        new GetMoneysAsyncTask(onQuery).execute();
    }

    public void getMoneys(MoneyResult onQuery) {
        new GetMoneysAsyncTask1(onQuery).execute();
    }

    public void insertMoneys(OnQuery<long[]> onQuery, MoneyLimit... moneyLimits) {
        new InsertMoneysAsyncTask(onQuery).execute(moneyLimits);

    }

    public void deleteMoneys(MoneyLimit... moneyLimits) {
        new DeleteMoneysAsyncTask().execute(moneyLimits);
    }

    private class InsertMoneysAsyncTask extends AsyncTask<MoneyLimit, Void, long[]> {
        OnQuery onQuery;

        public InsertMoneysAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }

        @Override
        protected long[] doInBackground(MoneyLimit... moneyLimits) {
            return moneyDatabase.moneyLimitDAO().insertMoney(moneyLimits);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(long[] longs) {
            super.onPostExecute(longs);
            this.onQuery.onResult(longs);
        }
    }
    private class GetMoneysAsyncTask extends AsyncTask<Void, Void, MoneyLimit> {
        OnQuery onQuery;

        public GetMoneysAsyncTask(OnQuery onQuery) {
            this.onQuery = onQuery;
        }
        @Override
        protected MoneyLimit doInBackground(Void... voids) {
            return moneyDatabase.moneyLimitDAO().getMoney();
        }

        @Override
        protected void onPostExecute(MoneyLimit moneyLimits) {
            super.onPostExecute(moneyLimits);
            onQuery.onResult(moneyLimits);
        }
    }
    private class GetMoneysAsyncTask1 extends AsyncTask<Void, Void, MoneyLimit> {
        MoneyResult onQuery;

        public GetMoneysAsyncTask1(MoneyResult onQuery) {
            this.onQuery = onQuery;
        }
        @Override
        protected MoneyLimit doInBackground(Void... voids) {
            return moneyDatabase.moneyLimitDAO().getMoney();
        }

        @Override
        protected void onPostExecute(MoneyLimit moneyLimits) {
            super.onPostExecute(moneyLimits);
            onQuery.keyQua(moneyLimits);
        }
    }
    private class DeleteMoneysAsyncTask extends AsyncTask<MoneyLimit, Void, Void> {


        public DeleteMoneysAsyncTask() {

        }

        @Override
        protected Void doInBackground(MoneyLimit... lists) {
            try{
                if (lists != null){
                    moneyDatabase.moneyLimitDAO().deleteMoney(lists);
                }
            }catch (NullPointerException e){

            }
            return null;
        }
    }
}
