package database.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import database.helper.DboHelper;
import database.model.UsuarioModel;

public class UsuarioDao extends AbstrataDao{

    private final String[]
    colunas = {
            UsuarioModel.COLUNA_ID_USUARIO,
            UsuarioModel.COLUNA_LOGIN,
            UsuarioModel.COLUNA_PASS
    };

    public UsuarioDao(Context context) {db_helper = new DboHelper(context);}

    public long Insert(UsuarioModel model){
        long linhasInseridas = 0;

        ContentValues values = new ContentValues();

        values.put(UsuarioModel.COLUNA_LOGIN, model.getLogin());
        values.put(UsuarioModel.COLUNA_PASS, model.getPass());

        try{
            Open();
            linhasInseridas = db.insert(UsuarioModel.TABELA_NOME,null,values);
        }
        finally {
            Close();
        }

        return linhasInseridas;
    }

    public boolean Select(final String login, final String pass) {

        boolean isExisteUsuario = false;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            UsuarioModel.TABELA_NOME,
                            colunas,
                            UsuarioModel.COLUNA_LOGIN + " = ? and "+UsuarioModel.COLUNA_PASS + " = ?",
                            new String[]{login, pass},
                            null,
                            null,
                            null
                    );
            if (cursor != null) {
                isExisteUsuario = cursor.getCount() > 0;
                cursor.close();
            }
        }
        finally {
            Close();
        }

        return isExisteUsuario;
    }

    @SuppressLint("Range")
    public int SelectUserIdByLogin(final String login) {
        int userId = -1;

        try {
            Open();

            Cursor cursor = db.query
                    (
                            UsuarioModel.TABELA_NOME,
                            new String[]{UsuarioModel.COLUNA_ID_USUARIO},
                            UsuarioModel.COLUNA_LOGIN + " = ? ",
                            new String[]{login},
                            null,
                            null,
                            null
                    );
            if (cursor != null && cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndex(UsuarioModel.COLUNA_ID_USUARIO));
                cursor.close();
            }
        } finally {
            Close();
        }

        return userId;
    }

}
