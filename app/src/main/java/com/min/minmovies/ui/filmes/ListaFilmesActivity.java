package com.min.minmovies.ui.filmes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.min.minmovies.R;
import com.min.minmovies.adapters.ListaFilmesAdapter;
import com.min.minmovies.data.model.Filme;
import com.min.minmovies.data.network.ApiService;
import com.min.minmovies.data.network.response.FilmesResult;
import com.min.minmovies.utils.Conexao;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        ApiService.getInstance().recuperaListaFilmesPopulares("22090c194e438ff8039642617a7308dc")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()) {
                             RecyclerView.LayoutManager  linearLayoutManager = new LinearLayoutManager(ListaFilmesActivity.this);
                             recycler.setLayoutManager(linearLayoutManager);
                             recycler.setAdapter(new ListaFilmesAdapter(response.body().getResults()));
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesResult> call, Throwable t) {

                    }
                });

        inicializaComponentes();
        eventosDeClick();
    }

    @Override
    protected void onStart() {
        super.onStart();

        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificarUsuario();
    }

    private void inicializaComponentes() {

    }

    private void verificarUsuario() {
        if(user == null) {
            finish();
        }
    }

    private void eventosDeClick() {

        // EVENTO LOGOUT / SAIR DA APLICAÇÃO
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Conexao.logout();
//                finish();
//            }
//        });

    }

    private List<Filme> criaFilmes() {
        return Arrays.asList(
            new Filme("Coração valente"),
            new Filme("Os guardiões da galáxia"),
            new Filme("Heman"),
            new Filme("Senhor dos anéis"),
            new Filme("Coração de ferro")
        );
    }

}
