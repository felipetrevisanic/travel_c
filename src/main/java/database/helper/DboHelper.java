package database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.dao.UsuarioDao;
import database.model.UsuarioModel;
import database.model.ViagemModel;


public class DboHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "bancoTravel.db";
    private static final int DATABASE_VERSAO = 3;

    public  DboHelper(Context context){
        super(context, DATABASE_NOME, null, DATABASE_VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioModel.CREATE_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UsuarioModel.DROP_TABLE);
        db.execSQL(ViagemModel.DROP_TABLE);

        db.execSQL(UsuarioModel.CREATE_TABLE);
        db.execSQL(ViagemModel.CREATE_TABLE);
    }
}
