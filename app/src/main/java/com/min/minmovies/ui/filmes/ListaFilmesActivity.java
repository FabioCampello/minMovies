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
import com.min.minmovies.utils.Conexao;

public class ListaFilmesActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        Toolbar toolbar = findViewById(R.id.toolbar);

        RecyclerView.LayoutManager  linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(new ListaFilmesAdapter());

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

}
