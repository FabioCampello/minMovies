package com.min.minmovies.ui.senha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.min.minmovies.utils.Conexao;
import com.min.minmovies.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private Button btn_enviar;
    private EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        inicializaComponentes();
        eventosDeClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void inicializaComponentes() {
        btn_enviar = (Button) findViewById(R.id.btnEnviar);
        edt_email = (EditText) findViewById(R.id.edtEmail);
    }

    private void eventosDeClick(){
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString().trim();
                validaDadosDeEntrada(email);
            }
        });
    }

    private void recuperaSenha(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(EsqueciSenhaActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            alert("Um email foi enviado para recuperação de sua senha.");
                            finish();
                        } else {
                            alert("O email fornecido não foi encontrado!");
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

    private void validaDadosDeEntrada(String email) {
        boolean isEmailValid = isValidEmailAddressRegex(email);
        if(!isEmailValid) {
            alert("E-mail inválido!");
            return;
        }
        recuperaSenha(email);
    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
