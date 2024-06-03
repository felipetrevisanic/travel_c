package database.dao;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import database.helper.DboHelper;

public class AbstrataDao {
    protected SQLiteDatabase db;
    protected DboHelper db_helper;

    protected final void Open() throws SQLException {
        db = db_helper.getWritableDatabase();
    }

    protected final void Close() throws SQLException {
        db_helper.close();
    }
}
