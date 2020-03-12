package com.min.minmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText edt_email;
    private EditText edt_senha;
    private Button btn_entrar;
    private Button btn_novo_cadastro;
    private Button btn_esqueci_senha;

    public static final String EMAIL = "email";
    public static final String SENHA = "senha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        inicializaComponentes();
        eventosDeClick();

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void inicializaComponentes() {
        edt_email = (EditText) findViewById(R.id.edtEmail);
        edt_senha = (EditText) findViewById(R.id.edtSenha);
        btn_entrar = (Button) findViewById(R.id.btnEntrar);
        btn_novo_cadastro = (Button) findViewById(R.id.btnNovoCadastro);
        btn_esqueci_senha = (Button) findViewById(R.id.btnEsqueciSenha);
    }

    private void eventosDeClick() {

        // EVENTO DE LOGIN
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString().trim();
                String senha = edt_senha.getText().toString().trim();
                validaDadosDeEntrada(email, senha);
            }
        });

        // EVENTO CADASTRAR NOVO USUÁRIO
        btn_novo_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // EVENTO ESQUECI A SENHA
        btn_esqueci_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EsqueciSenhaActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                        } else {
                            alert("Dados de acesso inválidos!");
                        }
                    }
                });
    }

    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    private void validaDadosDeEntrada(String email, String senha) {
        boolean isEmailValid = isValidEmailAddressRegex(email);
        if(!isEmailValid) {
            alert("E-mail inválido!");
            return;
        }

        if(senha.length() < 6) {
            alert("A senha deve ter no mínímo 6 digítos!");
            return;
        }
        login(email, senha);
    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
