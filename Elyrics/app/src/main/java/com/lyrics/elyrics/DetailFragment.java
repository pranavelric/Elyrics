package com.lyrics.elyrics;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class DetailFragment extends Fragment {
    private View v;
    private TextView mTitle, mArtist, mLyrics;
    private String title, singer, lyrics;
    private ImageButton copy;

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_detail, container, false);
        mTitle = v.findViewById(R.id.detailTitle);
        mArtist = v.findViewById(R.id.detailAtrist);
        mLyrics = v.findViewById(R.id.detaillyrics);
        copy = v.findViewById(R.id.copy);


        Bundle bundle = this.getArguments();
        if (bundle != null) {

            title = bundle.getString("title");
            singer = bundle.getString("artist");
            lyrics = bundle.getString("lyrics");

        }
        mTitle.setText(title);
        mArtist.setText(singer);
        mLyrics.setText(lyrics);


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("lyrics", mLyrics.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied to clipboard", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }


}
