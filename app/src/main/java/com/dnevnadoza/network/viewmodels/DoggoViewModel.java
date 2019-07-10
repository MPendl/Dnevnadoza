package com.dnevnadoza.network.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.dnevnadoza.network.models.DoggoResponse;
import com.dnevnadoza.network.repositories.DoggoRepository;

public class DoggoViewModel extends ViewModel {

    private DoggoRepository doggoRepository = DoggoRepository.getInstance();

    public LiveData<DoggoResponse> getDoggo() {
        return doggoRepository.getDoggo();
    }
}
