package com.example.travel_c5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import database.dao.UsuarioDao;
import database.dao.ViagemDao;
import database.model.ViagemModel;
import util.KeyUtil;

public class ViagemActivity extends AppCompatActivity {

    private Button btnNovaViagem,btnVoltar;
    ListView listaViagem;
    List<ViagemModel> viagens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnNovaViagem = findViewById(R.id.btnNovaViagem);
        listaViagem = findViewById(R.id.listViagem);

        btnNovaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ViagemActivity.this,HomeActivity.class));
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViagemActivity.this,MainActivity.class));
            }
        });


//        listaViagens(id);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String idString = extras.getString(KeyUtil.KEY_REGISTRO_ID);
            if (idString != null) {
                int id = Integer.parseInt(idString);
                listaViagens(1); // Uncomment this line if you want to call this method
            } else {
                Log.e("ViagemActivity", "No ID found in extras");
            }
        } else {
            Log.e("ViagemActivity", "No extras found in intent");
        }
    }

    public void listaViagens(long id){
        ViagemDao dao = new ViagemDao(this);
        viagens = dao.listar(id);

        ArrayAdapter<ViagemModel> adapter = new ArrayAdapter<ViagemModel>(this, android.R.layout.simple_list_item_1,viagens);
        listaViagem.setAdapter(adapter);
    }

//    private void CarregaViagens(){
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ViagemActivity.this);
//        String email = preferences.getString(KeyUtil.KEY_REGISTRO_EMAIL, "registro não encontra");
//
//        UsuarioDao user = new UsuarioDao(ViagemActivity.this);
//        long id = user.SelectUserIdByLogin(email);
//
//        ViagemDao dao = new ViagemDao(ViagemActivity.this);
//        dao.Select(id);
//    }
    
}
