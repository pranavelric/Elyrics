package com.lyrics.elyrics;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daasuu.bl.BubbleLayout;
import com.lyrics.elyrics.model.Quotes;
import com.lyrics.elyrics.viewmodel.QuotesViewModel;

import java.util.Random;

public class QuotesFragment extends Fragment {
    private TextView textView, author;
    private ConstraintLayout constraintLayout;
    private Button button;
    private QuotesViewModel quotesViewModel;
    private MutableLiveData<Quotes> mutableLiveData;
    private BubbleLayout bubbleLayout;

    public QuotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quotes, container, false);
        textView = v.findViewById(R.id.quote);
        button = v.findViewById(R.id.random);
        author = v.findViewById(R.id.author);
        bubbleLayout = v.findViewById(R.id.bubbleQuote);
        constraintLayout = v.findViewById(R.id.quotebg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quotesViewModel = new ViewModelProvider(QuotesFragment.this).get(QuotesViewModel.class);
                quotesViewModel.QuotesViewModelSetContext(container.getContext());
                mutableLiveData = quotesViewModel.getQuote();
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Quotes>() {
                    @Override
                    public void onChanged(Quotes quotes) {
                        textView.setText("\"" + quotes.getContent() + "\"");
                        author.setText("~" + quotes.getAuthor());

                        Random rnd = new Random();
                        int[] colors = {Color.argb(75, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                                ,
                                Color.argb(105, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                        };
                        GradientDrawable gd = new GradientDrawable(
                                GradientDrawable.Orientation.TOP_BOTTOM, colors);
                        gd.setCornerRadius(0f);
                        constraintLayout.setBackground(gd);
                        bubbleLayout.setBubbleColor(Color.argb(130, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));

                    }
                });


            }
        });


        return v;
    }
}
