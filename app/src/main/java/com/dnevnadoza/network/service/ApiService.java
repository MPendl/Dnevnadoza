package com.dnevnadoza.network.service;

import com.dnevnadoza.network.models.ChuckResponse;
import com.dnevnadoza.network.models.DoggoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("breeds/image/random")
    Call<DoggoResponse> getDoggo();

    @GET("jokes/random/10")
    Call<ChuckResponse> getJokes();

}
