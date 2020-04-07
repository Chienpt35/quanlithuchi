package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.DAO.KhoanChiDAO;
import com.example.duan1.KhoanChiActivity;
import com.example.duan1.ListKhoanChiActivity;
import com.example.duan1.R;
import com.example.duan1.model.KhoanChi;
import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.MyHolder> {
    List<KhoanChi> list;
    ListKhoanChiActivity context;
    LayoutInflater inflater;
    KhoanChiDAO khoanChiDAO;

    public KhoanChiAdapter(List<KhoanChi> list, ListKhoanChiActivity context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        khoanChiDAO = new KhoanChiDAO(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_khoanthu,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        KhoanChi khoanChi = list.get(i);
        myHolder.tvtTenKhoanThu.setText("Tên Khoản Chi: " + khoanChi.getTenKhoanChi());
        myHolder.tvtSoTienThu.setText("Số Tiền Chi: "+khoanChi.getSoTienChi());
        myHolder.tvtNgayThu.setText("Ngày Chi: "+khoanChi.getNgayChi());

        myHolder.imgDeleteKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanChi khoanChi1 = list.get(i);
                list.remove(khoanChi1);
                khoanChiDAO.deleteKhoanChi(khoanChi1);
                notifyDataSetChanged();
            }
        });
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanChi khoanChi2 = list.get(i);
                Intent intent = new Intent(context, KhoanChiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", khoanChi2.getId());
                bundle.putString("tenKhoanChi", khoanChi2.getTenKhoanChi());
                bundle.putInt("loaiChi", khoanChi2.getLoaiChi());
                bundle.putInt("soTienChi", khoanChi2.getSoTienChi());
                bundle.putString("ngayChi", khoanChi2.getNgayChi());
                bundle.putString("ghiChu", khoanChi2.getGhiChu());
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
