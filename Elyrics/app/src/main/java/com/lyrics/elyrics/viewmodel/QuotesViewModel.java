package com.lyrics.elyrics.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lyrics.elyrics.model.Quotes;
import com.lyrics.elyrics.service.QuotesRepository;

public class QuotesViewModel extends ViewModel {

    private Context context;
    private QuotesRepository repository;

    public void QuotesViewModelSetContext(Context context) {
        this.context = context;
        repository = new QuotesRepository(context);
    }



    public MutableLiveData<Quotes> getQuote(){
        return repository.getQuote();
    }
}
