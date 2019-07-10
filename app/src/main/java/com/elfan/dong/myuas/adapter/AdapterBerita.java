package com.elfan.dong.myuas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elfan.dong.myuas.DetailBeritaActivity;
import com.elfan.dong.myuas.R;
import com.elfan.dong.myuas.constanst.ConstantBerita;
import com.elfan.dong.myuas.response.BeritaItem;
import com.elfan.dong.myuas.response.DataItem;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    Context context;
    List<DataItem> berita;

    public AdapterBerita(Context context, List<DataItem> berita) {
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvJudul.setText(berita.get(i).getTitle());
        myViewHolder.tvTglTerbit.setText(berita.get(i).getCreatedAt());
        myViewHolder.tvPenulis.setText(berita.get(i).getDescription());
        final String urlGambarBerita = "http://tablo-app.herokuapp.com/images/" + berita.get(i).getImage();
        Glide.with(context).load(urlGambarBerita).into(myViewHolder.ivGambarBerita);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBeritaActivity.class);
                intent.putExtra(ConstantBerita.JUDULBERITA, berita.get(i).getTitle());
                intent.putExtra(ConstantBerita.TANGGALBERITA, berita.get(i).getCreatedAt());
                intent.putExtra(ConstantBerita.PENULISBERITA, berita.get(i).getDescription());
                intent.putExtra(ConstantBerita.FOTOBERITA, urlGambarBerita);
                intent.putExtra(ConstantBerita.ISIBERITA, berita.get(i).getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGambarBerita = itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = itemView.findViewById(R.id.tvPenulis);
        }
    }
}
