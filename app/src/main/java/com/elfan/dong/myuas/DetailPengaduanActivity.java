package com.elfan.dong.myuas;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.elfan.dong.myuas.helper.RealmHelper;
import com.elfan.dong.myuas.model.PengaduanModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailPengaduanActivity extends AppCompatActivity {

    public static final String KEY_ID = "key_id";
    RealmHelper realm;
    private EditText edJudul, edPengaduan, edTanggal;
    FloatingActionButton update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengaduan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        settanggal();
        final int dataID = getIntent().getIntExtra(KEY_ID, 0);
        final PengaduanModel pengaduanModel = realm.showonedata(dataID);
        edJudul.setText(pengaduanModel.getJudul());
        edPengaduan.setText(pengaduanModel.getPengaduan());
        edTanggal.setText(pengaduanModel.getTanggal());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edJudul.getText().toString().trim())){
                    edJudul.setError("Judul Tidak Boleh Kosong");
                }else if (TextUtils.isEmpty(edPengaduan.getText().toString().trim())){
                    edPengaduan.setError("Pengaduan Tidak Boleh Kosong");
                }else if (TextUtils.isEmpty(edTanggal.getText().toString().trim())){
                    edTanggal.setError("Tanggal Tidak Boleh Kosong");
                }else {
                    PengaduanModel pengaduanModel = new PengaduanModel();
                    pengaduanModel.setId(dataID);
                    pengaduanModel.setJudul(edJudul.getText().toString().trim());
                    pengaduanModel.setPengaduan(edPengaduan.getText().toString().trim());
                    pengaduanModel.setTanggal(edTanggal.getText().toString().trim());
                    realm.updatedata(pengaduanModel);
                    finish();
                    Toast.makeText(DetailPengaduanActivity.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void settanggal(){
        edTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(DetailPengaduanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        edTanggal.setText(dateFormat.format(cal.getTime()));
                    }
                }, nowYear, nowMonth, nowDay);
                dialog.show();
            }
        });
    }

    private void init() {
        realm = new RealmHelper(DetailPengaduanActivity.this);
        edJudul = findViewById(R.id.ed_judul);
        edPengaduan = findViewById(R.id.ed_pengaduan);
        edTanggal = findViewById(R.id.ed_tanggal);
        update = findViewById(R.id.update);
    }

    private void DialogDelete(){
        final int dataID = getIntent().getIntExtra(KEY_ID, 0);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("apakah data ini akan di hapus???");
        builder
                .setMessage("klik YA untuk hapus")
                .setIcon(R.drawable.ic_delete_black)
                .setCancelable(false)
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.deletedata(dataID);
                        finish();
                        Toast.makeText(DetailPengaduanActivity.this, "Data Berhasil diHapus", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_pengaduan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                DialogDelete();
                return true;
        }
        return false;
    }




}
