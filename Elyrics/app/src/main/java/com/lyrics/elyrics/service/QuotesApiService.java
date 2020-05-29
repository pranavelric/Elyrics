package com.lyrics.elyrics.service;

import com.lyrics.elyrics.model.Quotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuotesApiService {

@GET("random")
    Call<Quotes> getRandomQuote();
}
