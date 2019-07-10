package com.dnevnadoza.screens.chuck;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.TextView;

import com.dnevnadoza.R;
import com.dnevnadoza.network.viewmodels.ChuckViewModel;
import com.dnevnadoza.network.models.Chuck;

import java.util.ArrayList;

public class ChuckActivity extends AppCompatActivity {

    private ChuckViewModel chuckViewModel;
    private RecyclerView chuckRecyclerView;
    private SnapHelper snapHelper;

    private ArrayList<Chuck> jokes = new ArrayList<>();
    private JokesAdapter adapter;

    private Intent myShareIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck);
        chuckViewModel = ViewModelProviders.of(this).get(ChuckViewModel.class);
        initViews();
        initRecyclerView();
        subscribeUi();
    }

    private void subscribeUi() {
        chuckViewModel.getJokes().observe(this, chucks -> {
            jokes.addAll(chucks);
            adapter.notifyItemRangeInserted(jokes.size() - chucks.size(),
                    chucks.size());
        });
    }

    private void initViews() {
        chuckRecyclerView = findViewById(R.id.chuckRecyclerView);
        TextView shareButton = findViewById(R.id.shareButton);
        TextView nextButton = findViewById(R.id.nextButton);
        shareButton.setOnClickListener(view -> {
            createShareIntent(jokes.get(getCurrentPosition()).getJoke());
            startActivity(Intent.createChooser(myShareIntent, "Share this joke"));
        });
        nextButton.setOnClickListener(view -> nextJoke());
    }

    private void nextJoke() {
        chuckRecyclerView.smoothScrollToPosition(getCurrentPosition() + 1);
    }

    private void initRecyclerView() {
        chuckRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new JokesAdapter(jokes);
        chuckRecyclerView.setAdapter(adapter);
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(chuckRecyclerView);
        chuckRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (getCurrentPosition() == jokes.size() - 1) {
                    chuckViewModel.getJokes();
                }
            }
        });
    }

    private int getCurrentPosition() {
        View snapView = snapHelper.findSnapView(chuckRecyclerView.getLayoutManager());
        return chuckRecyclerView.getLayoutManager().getPosition(snapView);
    }

    private void createShareIntent(String joke) {
        myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(Intent.EXTRA_TEXT, joke);
        myShareIntent.putExtra(Intent.EXTRA_SUBJECT, "Vidi ovaj vic: ");
    }
}
