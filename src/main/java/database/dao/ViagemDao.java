package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.helper.DboHelper;
import database.model.ViagemModel;

public class ViagemDao extends AbstrataDao{

    private String[] colunas = new String[]
            {
                    ViagemModel.COLUNA_ID_VIAGEM,
                    ViagemModel.COLUNA_TOTAL_VIAJANTES,
                    ViagemModel.COLUNA_TOTAL_DIAS,
                    ViagemModel.COLUNA_DESTINO,
                    ViagemModel.COLUNA_TOTAL_KM,
                    ViagemModel.COLUNA_MEDIA_KM,
                    ViagemModel.COLUNA_CUSTO_L,
                    ViagemModel.COLUNA_TOTAL_VEICULOS,
                    ViagemModel.COLUNA_CUSTO_TARIFA,
                    ViagemModel.COLUNA_ALUGEL_VEICULO,
                    ViagemModel.COLUNA_CUSTO_REFEICAO,
                    ViagemModel.COLUNA_REFEICAO_DIA,
                    ViagemModel.COLUNA_CUSTO_NOITE,
                    ViagemModel.COLUNA_TOTAL_NOITE,
                    ViagemModel.COLUNA_TOTAL_QUARTO,
                    ViagemModel.COLUNA_TOTAL_DIVERSOS,
                    ViagemModel.COLUNA_TOTAL
            };

    public ViagemDao(Context context) {db_helper = new DboHelper(context);}

    public long Insert(ViagemModel model){
        long linhasInseridas = 0;

        ContentValues values = new ContentValues();
        values.put(ViagemModel.COLUNA_TOTAL_VIAJANTES, model.getTotalViajantes());
        values.put(ViagemModel.COLUNA_TOTAL_DIAS, model.getDuracaoViagem());
        values.put(ViagemModel.COLUNA_DESTINO, model.getDestino());
        values.put(ViagemModel.COLUNA_TOTAL_KM, model.getTotalKm());
        values.put(ViagemModel.COLUNA_MEDIA_KM, model.getMediakm());
        values.put(ViagemModel.COLUNA_CUSTO_L, model.getCustoLitro());
        values.put(ViagemModel.COLUNA_TOTAL_VEICULOS, model.getTotalVeiculos());
        values.put(ViagemModel.COLUNA_CUSTO_TARIFA, model.getCustoTarifa());
        values.put(ViagemModel.COLUNA_ALUGEL_VEICULO, model.getAluguelVeiculo());
        values.put(ViagemModel.COLUNA_CUSTO_REFEICAO, model.getCustoRefeicao());
        values.put(ViagemModel.COLUNA_REFEICAO_DIA, model.getRefeicaoDia());
        values.put(ViagemModel.COLUNA_CUSTO_NOITE, model.getCustoNoite());
        values.put(ViagemModel.COLUNA_TOTAL_NOITE, model.getTotalNoite());
        values.put(ViagemModel.COLUNA_TOTAL_QUARTO, model.getTotalQuarto());
        values.put(ViagemModel.COLUNA_TOTAL_DIVERSOS, model.getTotalDiversos());
        values.put(ViagemModel.COLUNA_TOTAL, model.getTotal());

        try {
            Open();
            linhasInseridas = db.insert(ViagemModel.TABELA_NOME, null, values);
        }
        finally {
            Close();
        }

        return linhasInseridas;
    }


    public List<ViagemModel> listar(long id){
        List<ViagemModel> lista = new ArrayList<ViagemModel>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + ViagemModel.TABELA_NOME + " WHERE " + ViagemModel.COLUNA_USER_ID + " = " + id,
                null
        );

        while(cursor.moveToNext()){
            lista.add(new ViagemModel(
                    cursor.getColumnIndex("_idViagem"),
                    cursor.getColumnIndex("totalViajantes"),
                    cursor.getColumnIndex("totalDias"),
                    cursor.getColumnIndex("destino"),
                    cursor.getColumnIndex("total"),
                    cursor.getColumnIndex("usuarioId")
            ));
        }

        return lista;
    }


}
