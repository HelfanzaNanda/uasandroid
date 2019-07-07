package com.elfan.dong.myuas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elfan.dong.myuas.constanst.ConstantBerita;

public class DetailBeritaActivity extends AppCompatActivity {

    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ivGambarBerita = findViewById(R.id.ivGambarBerita);
        tvTglTerbit = findViewById(R.id.tvTglTerbit);
        tvPenulis = findViewById(R.id.tvPenulis);

        wvKontenBerita = findViewById(R.id.wvKontenBerita);
        showDetailBerita();
    }

    private void showDetailBerita() {
        String judul_berita = getIntent().getStringExtra(ConstantBerita.JUDULBERITA);
        String tanggal_berita = getIntent().getStringExtra(ConstantBerita.TANGGALBERITA);
        String penulis_berita = getIntent().getStringExtra(ConstantBerita.PENULISBERITA);
        String isi_berita = getIntent().getStringExtra(ConstantBerita.ISIBERITA);
        String foto_berita = getIntent().getStringExtra(ConstantBerita.FOTOBERITA);

        getSupportActionBar().setTitle(judul_berita);
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tanggal_berita);
        Glide.with(DetailBeritaActivity.this).load(foto_berita).into(ivGambarBerita);

        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isi_berita,
                "text/html; charset=utf-8", "UTF-8");
    }
}
