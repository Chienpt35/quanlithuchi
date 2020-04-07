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

import com.example.duan1.DAO.KhoanThuDAO;
import com.example.duan1.KhoanThuActivity;
import com.example.duan1.ListKhoanThuActivity;
import com.example.duan1.R;
import com.example.duan1.model.KhoanThu;
import java.util.List;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.MyHolder> {
    ListKhoanThuActivity context;
    List<KhoanThu> list;
    LayoutInflater inflater;
    KhoanThuDAO khoanThuDAO;

    public KhoanThuAdapter(ListKhoanThuActivity context, List<KhoanThu> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanThuDAO = new KhoanThuDAO(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_khoanthu,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        KhoanThu khoanThu = list.get(i);
        myHolder.tvtTenKhoanThu.setText("Tên Khoản Thu: " + khoanThu.getTenKhoanThu());
        myHolder.tvtSoTienThu.setText("Số tiền thu: "+khoanThu.getSoTienThu());
        myHolder.tvtNgayThu.setText("Ngày thu: "+khoanThu.getNgayThu());

        myHolder.imgDeleteKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanThu khoanThu1 = list.get(i);
                list.remove(khoanThu1);
                khoanThuDAO.deleteKhoanThu(khoanThu1);
                notifyDataSetChanged();
            }
        });
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanThu khoanThu2 = list.get(i);
                Intent intent = new Intent(context, KhoanThuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", khoanThu2.getId());
                bundle.putString("tenKhoanThu", khoanThu2.getTenKhoanThu());
                bundle.putInt("loaithu", khoanThu2.getLoaithu());
                bundle.putInt("soTienThu", khoanThu2.getSoTienThu());
                bundle.putString("ngayThu", khoanThu2.getNgayThu());
                bundle.putString("ghiChu", khoanThu2.getGhiChu());
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
        public TextView tvtTenKhoanThu;
        public TextView tvtSoTienThu;
        public TextView tvtNgayThu;
        public ImageView imgDeleteKT;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvtTenKhoanThu = (TextView) itemView.findViewById(R.id.tvtTenKhoanThu);
            tvtSoTienThu = (TextView) itemView.findViewById(R.id.tvtSoTienThu);
            tvtNgayThu = (TextView) itemView.findViewById(R.id.tvtNgayThu);
            imgDeleteKT = (ImageView) itemView.findViewById(R.id.imgDeleteKT);
        }
    }
}
