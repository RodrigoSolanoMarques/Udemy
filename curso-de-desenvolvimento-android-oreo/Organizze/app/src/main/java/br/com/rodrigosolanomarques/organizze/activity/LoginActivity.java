package br.com.rodrigosolanomarques.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.com.rodrigosolanomarques.organizze.R;
import br.com.rodrigosolanomarques.organizze.config.ConfiguracaoFirebase;
import br.com.rodrigosolanomarques.organizze.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.buttonEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                // Validar se os campos foram preenchidos
                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarusuario();

                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void validarusuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            abrirTelaPrincipal();
                        } else {

                            String exception = "";

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                exception = "Usuário não está cadastrado.";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                exception = "Email e senha não correspondem a um usuário cadastrado.";
                            } catch (Exception e) {
                                exception = "Erro ao fazer login: " + e.getMessage();
                            }
                            Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}
