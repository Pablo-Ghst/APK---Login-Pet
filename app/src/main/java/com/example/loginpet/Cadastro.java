package com.example.loginpet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    TextInputEditText etRegEmail;
    TextInputEditText etCadastroSenha;
    Button btnCadastrar;
    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etRegEmail = findViewById(R.id.btnEmail);
        etCadastroSenha = findViewById(R.id.btnSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        mAuth = FirebaseAuth.getInstance();
        //Criando ação para o botão cadastrar
        btnCadastrar.setOnClickListener(view ->{
            CriarUsuario();
        });

    }
        //Método para verificar se os campos ficaram vazios.
    private void CriarUsuario(){
        String email = etRegEmail.getText().toString();
        String password = etCadastroSenha.getText().toString();

        if (TextUtils.isEmpty(email)){
        //Se os campos estiverem vazios, emite um aviso.
            etRegEmail.setError("O campo de Email não pode ficar vazio.");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etCadastroSenha.setError("O campo de senha não pode ficar vazio.");
            etCadastroSenha.requestFocus();
        }else{
        //Caso não esteja vazio, vamos efetuar a criação da conta no banco.
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
        //Caso os dados estiverem corretos, Cria a conta.
                        Toast.makeText(Cadastro.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Cadastro.this, Login.class));
        //Caso os dados estiverem incorretos, emite o erro.

                    }else{
                        Toast.makeText(Cadastro.this, "Erro no cadastro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }}