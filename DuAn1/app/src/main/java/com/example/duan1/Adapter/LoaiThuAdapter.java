package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.LoaiThuDAO;
import com.example.duan1.ListLoaiThuActivity;
import com.example.duan1.LoaiChiActivity;
import com.example.duan1.LoaiThuActivity;
import com.example.duan1.R;
import com.example.duan1.model.LoaiThu;

import java.util.List;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.MyHolder> {
    ListLoaiThuActivity context;
    List<LoaiThu> list;
    LayoutInflater inflater;
    LoaiThuDAO loaiThuDAO;

    public LoaiThuAdapter(ListLoaiThuActivity context, List<LoaiThu> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiThuDAO = new LoaiThuDAO(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_loaithu,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        final LoaiThu loaiThu = list.get(i);
        myHolder.tvtTenLoaiThu.setText("Tên Khoản Thu: "+loaiThu.getTenLoaiThu());
        myHolder.imgDeleteLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu loaiThu1 = list.get(i);
                list.remove(loaiThu1);
                loaiThuDAO.deleteLoaiThu(loaiThu1);
                notifyDataSetChanged();
            }
        });
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiThu loaiThu2 = list.get(i);
                Intent intent = new Intent(context, LoaiThuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",loaiThu2.getId());
                bundle.putString("tenLoaiThu",loaiThu2.getTenLoaiThu());
                intent.putExtra("bun",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tvtTenLoaiThu;
        public ImageView imgDeleteLT;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvtTenLoaiThu = (TextView) itemView.findViewById(R.id.tvtTenLoaiThu);
            imgDeleteLT = (ImageView) itemView.findViewById(R.id.imgDeleteLT);
        }
    }
}
