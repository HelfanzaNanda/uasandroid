package com.elfan.dong.myuas.network;

import com.elfan.dong.myuas.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
}
