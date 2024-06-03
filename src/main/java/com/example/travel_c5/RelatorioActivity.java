package com.example.travel_c5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import util.KeyUtil;

public class RelatorioActivity extends AppCompatActivity {

    TextView viajantes, dias, destino, custoPessoa, custoViagem;
    Button btnVoltar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        btnVoltar = findViewById(R.id.btnVoltarHome);
        viajantes = findViewById(R.id.totalViajantes);
        dias = findViewById(R.id.totalDuracao);
        destino = findViewById(R.id.totalDestino);
        custoPessoa = findViewById(R.id.totalCustoPessoa);
        custoViagem = findViewById(R.id.totalCustoViagem);

        Intent i = getIntent();

        Bundle extra = getIntent().getExtras();
        Integer totalViajantes = extra.getInt(KeyUtil.KEY_REGISTRO_TOTAL_VIAJANTES);
        Integer totaldias = extra.getInt(KeyUtil.KEY_REGISTRO_TOTAL_DURACAO);
        String totaldestino = extra.getString(KeyUtil.KEY_REGISTRO_TOTAL_DESTINO);
        Double totalCustoPessoa = extra.getDouble(KeyUtil.KEY_REGISTRO_TOTAL_CUSTO_PESSOA);
        Double totalCustoViagem = extra.getDouble(KeyUtil.KEY_REGISTRO_TOTAL_CUSTO_VIAGEM);

        String tv = totalViajantes.toString();
        String td = totaldias.toString();
        String tcp = totalCustoPessoa.toString();
        String tcv = totalCustoViagem.toString();

        viajantes.setText(tv);
        dias.setText(td); // Converta totaldias para String
        destino.setText(totaldestino);
        custoPessoa.setText(tcp);
        custoViagem.setText(tcv);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelatorioActivity.this,HomeActivity.class));
            }
        });
    }
}
