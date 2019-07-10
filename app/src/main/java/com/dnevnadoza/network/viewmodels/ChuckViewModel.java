package com.dnevnadoza.network.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.dnevnadoza.network.models.Chuck;
import com.dnevnadoza.network.repositories.ChuckRepository;

import java.util.List;

public class ChuckViewModel extends ViewModel {

    private ChuckRepository chuckRepository = ChuckRepository.getInstance();

    public LiveData<List<Chuck>> getJokes() {
        return chuckRepository.getJokes();
    }

}
