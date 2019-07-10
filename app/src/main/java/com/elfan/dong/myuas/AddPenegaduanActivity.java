package com.elfan.dong.myuas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.elfan.dong.myuas.adapter.AdapterPengaduan;
import com.elfan.dong.myuas.helper.RealmHelper;
import com.elfan.dong.myuas.model.PengaduanModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddPenegaduanActivity extends AppCompatActivity {
    private EditText edJudul, edPengaduan, edTanggal;
    RealmHelper realm;
    List<PengaduanModel> list = new ArrayList<>();
    RecyclerView recycler;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_penegaduan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        addData();
        setTanggal();
    }


    private void init(){
        edJudul = findViewById(R.id.ed_judul);
        edPengaduan = findViewById(R.id.ed_pengaduan);
        edTanggal = findViewById(R.id.ed_tanggal);
        add = findViewById(R.id.fab);
        realm = new RealmHelper(AddPenegaduanActivity.this);
    }


    private void addData() {
        add.setOnClickListener(new View.OnClickListener() {
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
                    pengaduanModel.setId((int) realm.getnextid());
                    pengaduanModel.setJudul(edJudul.getText().toString().trim());
                    pengaduanModel.setPengaduan(edPengaduan.getText().toString().trim());
                    pengaduanModel.setTanggal(edTanggal.getText().toString().trim());
                    realm.insertdata(pengaduanModel);
                    finish();
                    Toast.makeText(AddPenegaduanActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTanggal() {
        edTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddPenegaduanActivity.this, new DatePickerDialog.OnDateSetListener() {
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

}
