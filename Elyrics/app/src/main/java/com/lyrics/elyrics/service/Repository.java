package com.lyrics.elyrics.service;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.lyrics.elyrics.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    private String songName;
    private MutableLiveData<List<Song.content>> c = new MutableLiveData<>();
    Call<Song> song;

    public Repository(Context context, String songName) {
        this.context = context;
        this.songName = songName;
        ApiService apiService = RetroClass.getApiService();
        song = apiService.getSongInfo(songName);

    }

    public MutableLiveData<List<Song.content>> getSongList() {

        MutableLiveData<List<Song.content>> songData = new MutableLiveData<>();

        song.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                if (response.isSuccessful()) {
                    songData.setValue(response.body().getContent());
                } else {

                    Toast.makeText(context, "No such song found!", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {
                songData.setValue(null);
                Toast.makeText(context, "Some error occured" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return songData;

    }

}



