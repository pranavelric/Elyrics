package com.lyrics.elyrics.service;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesRetroClass {

   private static String BASE_URL ="https://api.quotable.io/";

    private  static Retrofit getInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static  QuotesApiService getApiService(){
        return getInstance().create(QuotesApiService.class);
    }



}
