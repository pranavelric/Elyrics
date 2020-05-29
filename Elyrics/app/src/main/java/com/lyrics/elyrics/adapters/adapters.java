package com.lyrics.elyrics.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.lyrics.elyrics.DetailFragment;
import com.lyrics.elyrics.R;
import com.lyrics.elyrics.model.Song;

import java.util.List;
import java.util.Random;

public class adapters extends RecyclerView.Adapter<adapters.SongViewHolder> {
    private Context context;
    private List<Song.content> songList;

    public adapters(Context context, List<Song.content> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_element, parent, false);
        return new SongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.artist.append(songList.get(position).getArtist());
        holder.title.setText(songList.get(position).getTitle());
        Random rnd = new Random();


        int[] colors = {Color.argb(75, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                ,
                Color.argb(105, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        };
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        holder.layout.setBackground(gd);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit = songList.get(position).getTitle();
                String artst = songList.get(position).getArtist();
                String lyrics = songList.get(position).getLyrics();

                Bundle bundle = new Bundle();
                bundle.putString("title", tit);
                bundle.putString("artist", artst);
                bundle.putString("lyrics", lyrics);

                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, detailFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView title, artist;
        private LinearLayout layout;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            artist = itemView.findViewById(R.id.artist);
            layout = itemView.findViewById(R.id.cardLayout);

        }
    }
}
