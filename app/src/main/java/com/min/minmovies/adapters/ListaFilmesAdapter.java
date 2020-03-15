package com.min.minmovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.min.minmovies.R;

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.ListaFilmesViewHolder> {

    // FIRST
    @NonNull
    @Override
    public ListaFilmesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);
        return new ListaFilmesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaFilmesViewHolder holder, int position) {
        holder.textTituloFilme.setText("Filme exemplo 2");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ListaFilmesViewHolder extends RecyclerView.ViewHolder {

        private TextView textTituloFilme;

        public ListaFilmesViewHolder(View itemView) {
            super(itemView);
            textTituloFilme = itemView.findViewById(R.id.textTituloFilme);

            textTituloFilme.setText("Filme de exemplo");
        }
    }

}
