package com.example.travel_c5;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import database.dao.ViagemDao;
import database.model.ViagemModel;
import util.DialogUtil;
import util.KeyUtil;

public class HomeActivity extends AppCompatActivity {


    private EditText totalViajantes,duracaoViagem,destino,totalkm,mediaL,custoL,totalVeiculos,refeicaoCusto,refeicaoDia,tarifaP,alugelCarro,custoNoite,totalNoite,totalQuarto,DiversosNome,DiversosCusto;
    private TextView gasolinaT;
    private TextView refeicaoT;
    private TextView tarifaT;
    private TextView hospedagemT;
    private TextView DiversosT;
    private CheckBox gasolinhaC,refeicaoC,tarifaC,hospedagemC;
    private Button btnSalvar, btnVoltar, btnAdicionar;
    private LinearLayout linear;
    private List<String> diversos = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        variáveis
        {
            totalViajantes = findViewById(R.id.homeTotalViajantes);
            duracaoViagem = findViewById(R.id.homeDuracaoViagem);
            destino = findViewById(R.id.homeDestino);
            totalkm = findViewById(R.id.gasolinaTotalKm);
            mediaL = findViewById(R.id.gasolinaMediaKmL);
            custoL = findViewById(R.id.gasolinaCustoMedio);
            totalVeiculos = findViewById(R.id.gasolinaTotalVeiculos);
            refeicaoCusto = findViewById(R.id.refeicaoCustoEstimado);
            refeicaoDia = findViewById(R.id.refeicaoRefeicaoDia);
            tarifaP = findViewById(R.id.tarifaCustoPessoa);
            alugelCarro = findViewById(R.id.tarifaAlugelCarro);
            custoNoite = findViewById(R.id.hospedagemCustoMedio);
            totalNoite = findViewById(R.id.hospedagemTotalNoites);
            totalQuarto = findViewById(R.id.hospedagemTotalQuartos);
            DiversosNome = findViewById(R.id.diversosNome);
            DiversosCusto = findViewById(R.id.diversosCusto);
            gasolinaT = findViewById(R.id.gasolinaTotal);
            refeicaoT = findViewById(R.id.refeicaoTotal);
            tarifaT = findViewById(R.id.tarifaTotal);
            hospedagemT = findViewById(R.id.hospedagemTotal);
            DiversosT = findViewById(R.id.diversosTotal);
            gasolinhaC = findViewById(R.id.adicionarGasolina);
            refeicaoC = findViewById(R.id.adicionarRefeicao);
            tarifaC = findViewById(R.id.tarifaAdicionar);
            hospedagemC = findViewById(R.id.hospedagemAdicionar);
            btnVoltar = findViewById(R.id.btnVoltar);
            btnSalvar = findViewById(R.id.btnSalvar);
            btnAdicionar= findViewById(R.id.diversosBtnAdicionar);
            linear = findViewById(R.id.diversosContainerAdicionar);
        }

//        textWatcher
        {
            TextWatcher tw = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    double totalViajantesT = 0.0;
                    double totalkmT = 0.0;
                    double mediaLT = 0.0;
                    double custoLT = 0.0;
                    int totalVeiculosT = 0;
                    double refeicaoCustoT = 0.0;
                    int refeicaoDiaT = 0;
                    double tarifaPT = 0.0;
                    double alugelCarroT = 0.0;
                    double custoNoiteT = 0.0;
                    int totalNoiteT = 0;
                    int totalQuartoT = 0;

                    DecimalFormat df = new DecimalFormat("#.00");




                    if (!totalViajantes.getText().toString().isEmpty()) {
                        totalViajantesT = Double.parseDouble(totalViajantes.getText().toString());
                    }
                    if (!totalkm.getText().toString().isEmpty()) {
                        totalkmT = Double.parseDouble(totalkm.getText().toString());
                    }
                    if (!mediaL.getText().toString().isEmpty()) {
                        mediaLT = Double.parseDouble(mediaL.getText().toString());
                    }
                    if (!custoL.getText().toString().isEmpty()) {
                        custoLT = Double.parseDouble(custoL.getText().toString());
                    }
                    if (!totalVeiculos.getText().toString().isEmpty()) {
                        totalVeiculosT = Integer.parseInt(totalVeiculos.getText().toString());
                    }
                    if (!refeicaoCusto.getText().toString().isEmpty()) {
                        refeicaoCustoT = Double.parseDouble(refeicaoCusto.getText().toString());
                    }
                    if (!refeicaoDia.getText().toString().isEmpty()) {
                        refeicaoDiaT = Integer.parseInt(refeicaoDia.getText().toString());
                    }
                    if (!tarifaP.getText().toString().isEmpty()) {
                        tarifaPT = Double.parseDouble(tarifaP.getText().toString());
                    }
                    if (!alugelCarro.getText().toString().isEmpty()) {
                        alugelCarroT = Double.parseDouble(alugelCarro.getText().toString());
                    }
                    if (!custoNoite.getText().toString().isEmpty()) {
                        custoNoiteT = Double.parseDouble(custoNoite.getText().toString());
                    }
                    if (!totalNoite.getText().toString().isEmpty()) {
                        totalNoiteT = Integer.parseInt(totalNoite.getText().toString());
                    }
                    if (!totalQuarto.getText().toString().isEmpty()) {
                        totalQuartoT = Integer.parseInt(totalQuarto.getText().toString());
                    }

                    String gasolinaTdf = df.format(((totalkmT/mediaLT)*custoLT)/totalVeiculosT);
                    String refeicaoTdf = df.format((refeicaoCustoT*refeicaoDiaT)*totalViajantesT);
                    String tarifaTdf =  df.format((tarifaPT*totalViajantesT)+alugelCarroT);
                    String hospedagemTdf =  df.format((custoNoiteT*totalQuartoT)*totalNoiteT);

                    if (!totalkm.getText().toString().isEmpty()&&
                            !mediaL.getText().toString().isEmpty()&&
                            !custoL.getText().toString().isEmpty()&&
                            !totalVeiculos.getText().toString().isEmpty()) {
                        gasolinaT.setText(gasolinaTdf);
                    }
                    if (!refeicaoCusto.getText().toString().isEmpty()&&
                            !mediaL.getText().toString().isEmpty()&&
                            !custoL.getText().toString().isEmpty()&&
                            !totalVeiculos.getText().toString().isEmpty()) {
                        gasolinaT.setText(gasolinaTdf);
                    }
                    if (!totalkm.getText().toString().isEmpty()&&
                            !refeicaoDia.getText().toString().isEmpty()) {
                        refeicaoT.setText(refeicaoTdf);
                    }
                    if (!tarifaP.getText().toString().isEmpty()&&
                            !alugelCarro.getText().toString().isEmpty()) {
                        tarifaT.setText(tarifaTdf);
                    }
                    if (!custoNoite.getText().toString().isEmpty()&&
                            !totalNoite.getText().toString().isEmpty()&&
                            !totalQuarto.getText().toString().isEmpty()) {
                        hospedagemT.setText(hospedagemTdf);
                    }

                }
            };

            totalViajantes.addTextChangedListener(tw);
            totalkm.addTextChangedListener(tw);
            mediaL.addTextChangedListener(tw);
            custoL.addTextChangedListener(tw);
            totalVeiculos.addTextChangedListener(tw);
            refeicaoCusto.addTextChangedListener(tw);
            refeicaoDia.addTextChangedListener(tw);
            tarifaP.addTextChangedListener(tw);
            alugelCarro.addTextChangedListener(tw);
            custoNoite.addTextChangedListener(tw);
            totalNoite.addTextChangedListener(tw);
            totalQuarto.addTextChangedListener(tw);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Verifica se os campos estão vazios conforme os campos de check
                {
                    if (!totalViajantes.getText().toString().isEmpty()) {
                        totalViajantes.setError("Este campo é obrigatório!");
                    }
                    else if (duracaoViagem.getText().toString().isEmpty()) {
                        duracaoViagem.setError("Este campo é obrigatório!");
                    }
                    else if (destino.getText().toString().isEmpty()) {
                        destino.setError("Este campo é obrigatório!");
                    }
                    if(gasolinhaC.isChecked()){
                        if (totalkm.getText().toString().isEmpty()) {
                            totalkm.setError("Este campo é obrigatório!");
                        }
                        else if (mediaL.getText().toString().isEmpty()) {
                            mediaL.setError("Este campo é obrigatório!");
                        }
                        else if (custoL.getText().toString().isEmpty()) {
                            custoL.setError("Este campo é obrigatório!");
                        }
                        else if (totalVeiculos.getText().toString().isEmpty()) {
                            totalVeiculos.setError("Este campo é obrigatório!");
                        }
                    }
                    else if(refeicaoC.isChecked()){
                        if (refeicaoCusto.getText().toString().isEmpty()) {
                            refeicaoCusto.setError("Este campo é obrigatório!");
                        }
                        else if (refeicaoDia.getText().toString().isEmpty()) {
                            refeicaoDia.setError("Este campo é obrigatório!");
                        }
                    }
                    else if(tarifaC.isChecked()){
                        if (tarifaP.getText().toString().isEmpty()) {
                            tarifaP.setError("Este campo é obrigatório!");
                        }
                        else if (alugelCarro.getText().toString().isEmpty()) {
                            alugelCarro.setError("Este campo é obrigatório!");
                        }
                    }
                    else if(hospedagemC.isChecked()){
                        if (custoNoite.getText().toString().isEmpty()) {
                            custoNoite.setError("Este campo é obrigatório!");
                        }
                        else if (totalNoite.getText().toString().isEmpty()) {
                            totalNoite.setError("Este campo é obrigatório!");
                        }
                        else if (totalQuarto.getText().toString().isEmpty()) {
                            totalQuarto.setError("Este campo é obrigatório!");
                        }
                    }
                }

//                salva no banco os que tem check e os que não tem possuem valor 0
                {
                    ViagemDao dao = new ViagemDao(HomeActivity.this);
                    ViagemModel model = new ViagemModel();

                    model.setTotalViajantes(Integer.parseInt(totalViajantes.getText().toString()));
                    model.setDuracaoViagem(Integer.parseInt(duracaoViagem.getText().toString()));
                    model.setDestino(destino.getText().toString());

                    if(gasolinhaC.isChecked()){
                        model.setTotalKm(Double.parseDouble(totalkm.getText().toString()));
                        model.setMediakm(Double.parseDouble(mediaL.getText().toString()));
                        model.setCustoLitro(Double.parseDouble(custoL.getText().toString()));
                        model.setTotalVeiculos(Integer.parseInt(totalVeiculos.getText().toString()));
                    }else{
                        model.setTotalKm(0);
                        model.setMediakm(0);
                        model.setCustoLitro(0);
                        model.setTotalVeiculos(0);
                    }

                    if(refeicaoC.isChecked()){
                        model.setRefeicaoDia(Integer.parseInt(refeicaoDia.getText().toString()));
                        model.setCustoRefeicao(Double.parseDouble(refeicaoCusto.getText().toString()));
                    }else {
                        model.setRefeicaoDia(0);
                        model.setCustoRefeicao(0);
                    }

                    if(tarifaC.isChecked()){
                        model.setCustoTarifa(Double.parseDouble(tarifaT.getText().toString()));
                        model.setAluguelVeiculo(Double.parseDouble(alugelCarro.getText().toString()));
                    }else{
                        model.setCustoTarifa(0);
                        model.setAluguelVeiculo(0);
                    }

                    if(hospedagemC.isChecked()){
                        model.setCustoNoite(Double.parseDouble(custoNoite.getText().toString()));
                        model.setTotalNoite(Integer.parseInt(totalNoite.getText().toString()));
                        model.setTotalQuarto(Integer.parseInt(totalQuarto.getText().toString()));
                    }else{
                        model.setCustoNoite(0);
                        model.setTotalNoite(0);
                        model.setTotalQuarto(0);
                    }

                    model.setTotal();
                    Intent i = new Intent(HomeActivity.this, RelatorioActivity.class);
                    i.putExtra(KeyUtil.KEY_REGISTRO_TOTAL_VIAJANTES, model.getTotalViajantes());
                    i.putExtra(KeyUtil.KEY_REGISTRO_TOTAL_DURACAO, model.getDuracaoViagem());
                    i.putExtra(KeyUtil.KEY_REGISTRO_TOTAL_DESTINO, model.getDestino());
                    i.putExtra(KeyUtil.KEY_REGISTRO_TOTAL_CUSTO_PESSOA, model.getTotal()/model.getTotalViajantes());
                    i.putExtra(KeyUtil.KEY_REGISTRO_TOTAL_CUSTO_VIAGEM, model.getTotal());

                    startActivity(i);

                }
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeAtracao = DiversosNome.getText().toString();
                String custoAtracao = DiversosCusto.getText().toString();

                if (!nomeAtracao.isEmpty() && !custoAtracao.isEmpty()) {
                    double valorCustoAtracao = Double.parseDouble(custoAtracao);
                    diversos.add(nomeAtracao + ": R$ " + String.format("%.2f", valorCustoAtracao));

                    linear.removeAllViews();

                    for (int i = 0; i < diversos.size(); i++) {
                        final int index = i; // para referência dentro do OnClickListener

                        // Cria um LinearLayout horizontal
                        LinearLayout itemLayout = new LinearLayout(HomeActivity.this);
                        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                        // Cria o TextView para o item
                        TextView novoItem = new TextView(HomeActivity.this);
                        novoItem.setText(diversos.get(index));
                        novoItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        novoItem.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                        novoItem.setTypeface(ResourcesCompat.getFont(HomeActivity.this, R.font.nunito_bold));
                        novoItem.setPadding(0, 10, 0, 0);

                        // Cria o ImageButton para o botão de exclusão
                        ImageButton deleteButton = new ImageButton(HomeActivity.this);
                        deleteButton.setImageResource(android.R.drawable.ic_delete); // Usar um ícone de delete padrão
                        deleteButton.setBackgroundColor(Color.TRANSPARENT); // Fundo transparente
                        deleteButton.setPadding(20, 0, 0, 0);

                        // Define o OnClickListener para o botão de exclusão
                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                diversos.remove(index); // Remove o item da lista
                                linear.removeView(itemLayout); // Remove o layout do item da tela
                                double total = calcularTotal(); // Recalcula o total
                                DiversosT.setText("Total: R$ " + String.format("%.2f", total));
                            }
                        });

                        // Adiciona o TextView e o ImageButton ao LinearLayout horizontal
                        itemLayout.addView(novoItem);

                        // Define as margens do layout do item
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 0, 0, 20);
                        itemLayout.setLayoutParams(params);

                        // Adiciona o ImageButton ao final do itemLayout
                        itemLayout.addView(deleteButton);

                        // Adiciona o layout do item ao LinearLayout principal
                        linear.addView(itemLayout, params);
                    }
                    double total = calcularTotal();
                    DiversosT.setText("Total: R$ " + String.format("%.2f", total));
                    DiversosNome.setText("");
                    DiversosCusto.setText("");
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,RelatorioActivity.class));
            }
        });



    }

    private double calcularTotal() {
        double total = 0.0;

        for (String item : diversos) {
            String[] partes = item.split(": R\\$ ");
            if (partes.length == 2) {
                double valor = Double.parseDouble(partes[1]);
                total += valor;
            }
        }

        return total;
    }
}
