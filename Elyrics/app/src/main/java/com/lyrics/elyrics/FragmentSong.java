package com.lyrics.elyrics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lyrics.elyrics.adapters.adapters;
import com.lyrics.elyrics.model.Song;
import com.lyrics.elyrics.viewmodel.SongViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSong extends Fragment {

    private EditText enter;
    private View v;
    private SongViewModel songViewModel;
    private MutableLiveData<List<Song.content>> songList;
    private adapters adapter;
    private RecyclerView recyclerView;
    private ImageButton search;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout layout;

    public FragmentSong() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_song, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        enter = v.findViewById(R.id.enter);
        search = v.findViewById(R.id.search);
        layout = v.findViewById(R.id.loading);
        collapsingToolbarLayout = v.findViewById(R.id.collapToolBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams)
                collapsingToolbarLayout.getLayoutParams();

        params.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

        collapsingToolbarLayout.setLayoutParams(params);

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isNetworkAvailable()) {
                Log.d("RR", "onCrerrateView: "+isNetworkAvailable());


            layout.setVisibility(View.VISIBLE);
            if (!enter.getText().equals("") && enter.getText() != null && enter.getText().length() > 0) {


                final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams)
                        collapsingToolbarLayout.getLayoutParams();

                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

                collapsingToolbarLayout.setLayoutParams(params);


                songViewModel = new ViewModelProvider(FragmentSong.this).get(SongViewModel.class);
                songViewModel.setContext(container.getContext(), enter.getText().toString());
                songList = songViewModel.getSong();


                songList.observe(getViewLifecycleOwner(), new Observer<List<Song.content>>() {

                            @Override
                            public void onChanged(List<Song.content> contents) {

                                layout.setVisibility(View.GONE);
                                adapter = new adapters(getContext(), contents);
                                recyclerView.setAdapter(adapter);

                            }


                        }


                );

            } else {
                layout.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Enter a song name", Toast.LENGTH_SHORT).show();

            }
        }else{
                Toast.makeText(getContext(), "No internet Connection", Toast.LENGTH_LONG).show();
            }

        }
    });



        return v;
    }





    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }








}
