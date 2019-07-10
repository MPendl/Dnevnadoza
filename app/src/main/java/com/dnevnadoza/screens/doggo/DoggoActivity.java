package com.dnevnadoza.screens.doggo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dnevnadoza.R;
import com.dnevnadoza.network.viewmodels.DoggoViewModel;

public class DoggoActivity extends AppCompatActivity {

    private DoggoViewModel doggoViewModel;
    private ImageView doggoImage;
    private TextView shareButton;
    private TextView newButton;
    private Intent myShareIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doggo);
        doggoViewModel = ViewModelProviders.of(this).get(DoggoViewModel.class);
        initViews();
        initListeners();
        getDoggo();
    }

    private void getDoggo() {
        doggoViewModel.getDoggo().observe(this, doggoResponse -> {
            Glide.with(doggoImage)
                    .load(doggoResponse.getMessage())
                    .into(doggoImage);
            createShareIntent(doggoResponse.getMessage());
        });
    }

    private void initViews() {
        doggoImage = findViewById(R.id.doggoImage);
        shareButton = findViewById(R.id.shareDoggo);
        newButton = findViewById(R.id.newButton);
    }

    private void initListeners() {
        newButton.setOnClickListener(view -> getDoggo());
        shareButton.setOnClickListener(view -> {
            startActivity(Intent.createChooser(myShareIntent, "Share this doggo"));
        });
    }

    private void createShareIntent(String doggoURL) {
        myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(Intent.EXTRA_SUBJECT, "Vidi ovog psa: ");
        myShareIntent.putExtra(Intent.EXTRA_TEXT, doggoURL);
    }

}
