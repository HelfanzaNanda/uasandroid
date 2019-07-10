package com.elfan.dong.myuas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elfan.dong.myuas.DetailPengaduanActivity;
import com.elfan.dong.myuas.R;
import com.elfan.dong.myuas.model.PengaduanModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterPengaduan extends RecyclerView.Adapter<AdapterPengaduan.MyViewHolder> {

    Context context;
    List<PengaduanModel> data = new ArrayList<>();

    public AdapterPengaduan(Context context, List<PengaduanModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pengaduan_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvJudul.setText(data.get(i).getJudul());
        myViewHolder.tvPengaduan.setText(data.get(i).getPengaduan());
        myViewHolder.tvTanggal.setText(data.get(i).getTanggal());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPengaduanActivity.class);
                intent.putExtra(DetailPengaduanActivity.KEY_ID, data.get(i).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvPengaduan, tvTanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvPengaduan = itemView.findViewById(R.id.tvPengaduan);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }
}
