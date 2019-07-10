package com.elfan.dong.myuas.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PengaduanModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String judul, pengaduan, tanggal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengaduan() {
        return pengaduan;
    }

    public void setPengaduan(String pengaduan) {
        this.pengaduan = pengaduan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
