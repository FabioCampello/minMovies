package com.min.minmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CadastroUsuarioActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private Button btn_cadastrar;
    private EditText edt_email;
    private EditText edt_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        auth = FirebaseAuth.getInstance();

        inicializaComponentes ();
        eventosDeClick();

    }

    private void inicializaComponentes() {
        edt_email = (EditText) findViewById(R.id.edtEmail);
        edt_senha = (EditText) findViewById(R.id.edtSenha);
        btn_cadastrar = (Button) findViewById(R.id.btnCadastrar);
    }

    private void eventosDeClick() {

        // EVENTO CADASTRAR NOVO USUÁRIO
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString().trim();
                String senha = edt_senha.getText().toString().trim();
                validaDadosDeEntrada(email, senha);
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
        criarUser(email, senha);
//        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//        startActivity(intent);
        finish();
    }

    private void criarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso!");
                            edt_email.setText("");
                            edt_senha.setText("");
                        } else {
                            alert("Não foi possível realizar o cadastro!");
                        }
                    }
                });
    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
