package com.example.rodrigomarques.firebaseapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // Manipula o banco de dados
   /* private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    // Manipula os usuários
    private FirebaseAuth usuario = FirebaseAuth.getInstance();*/


    private ImageView imageFoto;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFoto = findViewById(R.id.imageFoto);
        buttonUpload = findViewById(R.id.buttonFoto);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Configura para imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                // Recupera bitmap da imagem (image a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                // Comprimo bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                // Converte o baos para pixel brutos em uma matriz de bytes
                // (dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                // Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");

                // Nome da imagem
                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");

                // Retorna objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Upload da imagem falhou: " + e.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri url = taskSnapshot.getDownloadUrl();
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao fazer upload: " + url.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });







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
        DatabaseReference produtos = reference.child("produtos");
        DatabaseReference usuarios = reference.child("usuarios");*/


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

        /*Gerar Identificador unico
        Usuario usuario = new Usuario();
        usuario.setNome("Mario");
        usuario.setSobrenome("Super");
        usuario.setIdade(20);

        usuarios.push().setValue(usuario);*/

        /*Filtro*/

//        DatabaseReference usuarioPesquisa = usuarios.child("-LG_aGuNtz0lFBlhoXcR");

//        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Solano");
//        Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(3);
//        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(3);

        // Maior ou igual (>=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(45);

        // Menor ou igual (<=)
        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(30);

        //Entre doi valores
//        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(20).endAt(30);

        /* Filtrar palavras
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("Maria").endAt("Mariu" + "\uf8ff");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
//                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
//                    Log.i("Dados do usuário", "nome: " + usuario.getNome());
                }

                Log.i("Dados do usuário", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
