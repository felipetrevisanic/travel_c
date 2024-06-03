package com.example.travel_c5;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import database.dao.UsuarioDao;
import database.model.UsuarioModel;
import util.DialogUtil;
import util.KeyUtil;

public class CadastroActivity extends AppCompatActivity {

    private Button btnRegistro, btnVoltar;
    private EditText email,senha,confimacao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.registro_email);
        senha = findViewById(R.id.registroSenha);
        confimacao = findViewById(R.id.registroConfirmacao);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    email.setError("Campo nome obrigatório!");
                    email.setEnabled(true);
                }
                else if (!senha.getText().toString().equals(confimacao.getText().toString())) {
                    senha.setText("");
                    confimacao.setText("");
                    senha.requestFocus();
                    Toast.makeText(CadastroActivity.this, "Senhas diferentes!", Toast.LENGTH_LONG).show();
                } else {

                    UsuarioDao dao = new UsuarioDao(CadastroActivity.this);

                    UsuarioModel model = new UsuarioModel();
                    model.setLogin(email.getText().toString());
                    model.setPass(senha.getText().toString());

                    long linhasInseridas = dao.Insert(model);
                    if (linhasInseridas > 0) {
                        Intent it = new Intent();
                        it.putExtra(KeyUtil.KEY_REGISTRO_EMAIL, email.getText().toString());
                        it.putExtra(KeyUtil.KEY_REGISTRO_SENHA, senha.getText().toString());

                        setResult(1, it);
                        finish();

                        startActivity(new Intent(CadastroActivity.this, HomeActivity  .class));
                    }
                    else {
                        DialogUtil.showError(CadastroActivity.this, "Erro", "Falha ao inserir o usuário no banco de dados!");
                    }
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, MainActivity.class));
            }
        });

    }
}
