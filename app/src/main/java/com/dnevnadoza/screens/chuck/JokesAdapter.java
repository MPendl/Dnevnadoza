package com.dnevnadoza.screens.chuck;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dnevnadoza.R;
import com.dnevnadoza.network.models.Chuck;

import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeHolder> {

    private ArrayList<Chuck> jokes;

    public JokesAdapter(ArrayList<Chuck> jokes) {
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public JokeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new JokeHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_chuck, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JokeHolder jokeHolder, int i) {
        Chuck chuck = jokes.get(i);
        TextView jokeTextView = jokeHolder.itemView.findViewById(R.id.jokeTextView);
        jokeTextView.setText(chuck.getJoke());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    class JokeHolder extends RecyclerView.ViewHolder {
        JokeHolder(View itemView) {
            super(itemView);
        }
    }
}
