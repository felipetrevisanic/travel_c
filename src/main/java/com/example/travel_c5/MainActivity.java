package com.example.travel_c5;

import static com.example.travel_c5.R.id.editLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import database.dao.UsuarioDao;
import database.model.UsuarioModel;
import database.model.ViagemModel;
import util.DialogUtil;
import util.KeyUtil;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin, editSenha;
    private Button btnLogin, btnLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editLogin.getText().toString().isEmpty()) {
                    editLogin.setError("Campo e-mail é obrigatório!");
                }
                else if (editSenha.getText().toString().isEmpty()) {
                    editSenha.setError("Campo senha é obrigatório!");
                }
                else {

                    UsuarioDao dao = new UsuarioDao(MainActivity.this);
                    UsuarioModel model = new UsuarioModel();
                    boolean isUsuarioEncontrado = dao.Select(editLogin.getText().toString(), editSenha.getText().toString());
                    if (isUsuarioEncontrado) {
                        Intent it = new Intent(MainActivity.this, ViagemActivity.class);

                        it.putExtra(KeyUtil.KEY_REGISTRO_ID, model.getId());
                        startActivity(it);
                    }
                    else {
                        DialogUtil.showError(MainActivity.this, "Erro" ,"Usuário inexistente!");
                    }
                }
            }
        });

        btnLocal = findViewById(R.id.btnLoginLocal);
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CadastroActivity.class));
            }
        });

    }
}