package com.lyrics.elyrics.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lyrics.elyrics.model.Song;
import com.lyrics.elyrics.service.Repository;

import java.util.List;

public class SongViewModel extends ViewModel {

    private Context context;
    private Repository repo;

    public void setContext(Context context,String songName) {
        this.context = context;
        repo = new Repository(context,songName);
//        repo.getSongList();
    }




public MutableLiveData<List<Song.content>> getSong(){
return repo.getSongList();
}



}
