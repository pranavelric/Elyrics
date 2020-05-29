package com.lyrics.elyrics.service;

import com.lyrics.elyrics.model.Song;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("{song}")
    Call<Song> getSongInfo(@Path("song") String song);
}
