package com.elfan.dong.myuas.network;

import com.elfan.dong.myuas.response.ResponseBerita;
import com.elfan.dong.myuas.response.ResponseNews;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET("news")
    Call<ResponseNews> request_show_all_berita();
}
