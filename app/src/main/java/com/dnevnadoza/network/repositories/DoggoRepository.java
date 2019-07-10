package com.dnevnadoza.network.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.dnevnadoza.network.service.ApiService;
import com.dnevnadoza.network.service.ApiServiceFactory;
import com.dnevnadoza.network.models.DoggoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoggoRepository {

    private static DoggoRepository instance;

    public static DoggoRepository getInstance() {
        if (instance == null)
            instance = new DoggoRepository();
        return instance;
    }

    private ApiService apiService;
    private MutableLiveData<DoggoResponse> doggoLiveData = new MutableLiveData<>();

    private DoggoRepository() {
        setupRepository();
    }

    private void setupRepository() {
        doggoLiveData = new MutableLiveData<>();
        apiService = ApiServiceFactory.getDoggoApiService();
    }

    public MutableLiveData<DoggoResponse> getDoggo() {
        Call<DoggoResponse> getDoggo = apiService.getDoggo();
        getDoggo.enqueue(new Callback<DoggoResponse>() {
            @Override
            public void onResponse(Call<DoggoResponse> call, Response<DoggoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    doggoLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DoggoResponse> call, Throwable t) {
            }
        });
        return doggoLiveData;
    }
}
