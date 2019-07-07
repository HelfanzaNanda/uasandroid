package com.elfan.dong.myuas.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

    public static String API_URL = "http://10.148.231.144/myuas-api/";

    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
