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

import com.example.duan1.DAO.LoaiChiDAO;
import com.example.duan1.ListLoaiChiActivity;
import com.example.duan1.LoaiChiActivity;
import com.example.duan1.R;
import com.example.duan1.model.LoaiChi;

import java.util.List;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.MyHolder> {
    ListLoaiChiActivity context;
    List<LoaiChi> list;
    LayoutInflater layoutInflater;
    LoaiChiDAO loaiChiDAO;

    public LoaiChiAdapter(ListLoaiChiActivity context, List<LoaiChi> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiChiDAO = new LoaiChiDAO(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_loaithu,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        LoaiChi loaiChi = list.get(i);
        myHolder.tvtTenLoaiThu.setText("Tên Loại Chi: "+ loaiChi.getTenLoaiChi());
        myHolder.imgDeleteLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi loaiChi1 = list.get(i);
                list.remove(loaiChi1);
                loaiChiDAO.deleteLoaiChi(loaiChi1);
                notifyDataSetChanged();
            }
        });
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi loaiChi2 = list.get(i);
                Intent intent = new Intent(context, LoaiChiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",loaiChi2.getId());
                bundle.putString("tenLoaiChi",loaiChi2.getTenLoaiChi());
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
