package com.dnevnadoza.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dnevnadoza.R;
import com.dnevnadoza.screens.chuck.ChuckActivity;
import com.dnevnadoza.screens.doggo.DoggoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Button psiButton = findViewById(R.id.psiButton);
        psiButton.setOnClickListener(listener);
        Button chuckButton = findViewById(R.id.chuckButton);
        chuckButton.setOnClickListener(listener);
    }

    View.OnClickListener listener = view -> {
        Intent intent;
        switch (view.getId()) {
            case R.id.psiButton:
                intent = new Intent(getBaseContext(), DoggoActivity.class);
                startActivity(intent);
                break;
            case R.id.chuckButton:
                intent = new Intent(getBaseContext(), ChuckActivity.class);
                startActivity(intent);
                break;
        }
    };
}
