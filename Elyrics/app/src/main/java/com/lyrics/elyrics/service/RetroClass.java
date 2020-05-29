package com.lyrics.elyrics.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    public static final String BASE_URL="https://api.canarado.xyz/lyrics/";

    private  static Retrofit getInstance(){
    return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
            .build();

    }

    public static  ApiService getApiService(){
        return getInstance().create(ApiService.class);
    }

}
