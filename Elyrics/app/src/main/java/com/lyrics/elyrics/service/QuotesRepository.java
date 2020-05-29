package com.lyrics.elyrics.service;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.lyrics.elyrics.model.Quotes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesRepository {
    private Context context;

    private MutableLiveData<Quotes> mutableLiveData = new MutableLiveData<>();
    Call<Quotes> quoteCall;
    public QuotesRepository(Context context) {
        this.context = context;

        QuotesApiService quotesApiService = QuotesRetroClass.getApiService();
    quoteCall = quotesApiService.getRandomQuote();
    }

    public MutableLiveData<Quotes> getQuote() {

        MutableLiveData<Quotes> quotesData = new MutableLiveData<>();
quoteCall.enqueue(new Callback<Quotes>() {
    @Override
    public void onResponse(Call<Quotes> call, Response<Quotes> response) {

        if(response.isSuccessful()) {
            quotesData.setValue(response.body());
        }
        else{
            Toast.makeText(context, "Some error occured", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFailure(Call<Quotes> call, Throwable t) {
        quotesData.setValue(null);
        Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show();
    }
});

        return quotesData;

    }



}
