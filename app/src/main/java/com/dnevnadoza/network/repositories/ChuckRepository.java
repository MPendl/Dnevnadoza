package com.dnevnadoza.network.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.dnevnadoza.network.service.ApiService;
import com.dnevnadoza.network.service.ApiServiceFactory;
import com.dnevnadoza.network.models.Chuck;
import com.dnevnadoza.network.models.ChuckResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuckRepository {

    private static ChuckRepository instance;

    public static ChuckRepository getInstance() {
        if (instance == null)
            instance = new ChuckRepository();
        return instance;
    }

    private ApiService apiService;
    private MutableLiveData<List<Chuck>> chuckLiveData = new MutableLiveData<>();

    private ChuckRepository() {
        setupRepository();
    }

    private void setupRepository() {
        chuckLiveData = new MutableLiveData<>();
        chuckLiveData.setValue(new ArrayList<>());
        apiService = ApiServiceFactory.getChuckApiService();
    }

    public MutableLiveData<List<Chuck>> getJokes() {
        Call<ChuckResponse> call = apiService.getJokes();
        call.enqueue(new Callback<ChuckResponse>() {
            @Override
            public void onResponse(Call<ChuckResponse> call, Response<ChuckResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chuckLiveData.postValue(response.body().getValue());
                }
            }

            @Override
            public void onFailure(Call<ChuckResponse> call, Throwable t) {
            }
        });
        return chuckLiveData;
    }

}
