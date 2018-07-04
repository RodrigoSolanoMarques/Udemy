package com.example.rodrigomarques.firebaseapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Manipula o banco de dados
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    // Manipula os usuários
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Criação de usuário
        usuario.createUserWithEmailAndPassword("rodrigo@gmail.com", "ro0430")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("CreateUser", "Sucesso ao cadastrar usuário");
                        } else {
                            Log.i("CreateUser", "Erro ao cadastrar usuário");
                        }
                    }
                });*/

        /*Logar Usuário
        usuario.signInWithEmailAndPassword("rodrigo@gmail.com", "ro0430")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("SignIn", "Sucesso ao logar usuário");
                        } else {
                            Log.i("SignIn", "Erro ao logar usuário");
                        }
                    }
                });*/


        /*Deslogar usuário*/
//        usuario.signOut();

        /* Verificar usuario logado
        if (usuario.getCurrentUser() != null) {
            Log.i("CurrentUser", "Usuário Logado.");
        }else{
            Log.i("CurrentUser", "Usuário não Logado.");
        }*/

        /* Referencia de Nós para persistencia
        DatabaseReference produtos = reference.child("produtos");*/
        DatabaseReference usuarios = reference.child("usuarios");


        /* Salvar dados no Firebase
        Usuario usuario = new Usuario();
        usuario.setNome("Solano");
        usuario.setSobrenome("Marques");
        usuario.setIdade(18);

        usuarios.child("002").setValue(usuario);

        Produto produto = new Produto();
        produto.setDescricao("Nexus");
        produto.setMarca("LG");
        produto.setPreço(999.99);

        produtos.child("002").setValue(produto);*/

        /* Linstener - Alteração de Dados
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        /*Gerar Identificador unico*/
        Usuario usuario = new Usuario();
        usuario.setNome("Rodrigo");
        usuario.setSobrenome("Marques");
        usuario.setIdade(25);

        usuarios.push().setValue(usuario);
    }
}
