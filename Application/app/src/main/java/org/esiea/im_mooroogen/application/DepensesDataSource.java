package org.esiea.im_mooroogen.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daren on 21/11/2015.
 */
public class DepensesDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumn = {MySQLiteHelper.DEPENSE_ID, MySQLiteHelper.DEPENSE_MONTANT};

    public DepensesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
    }

    public void open() throws Exception{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Depenses createDepense(String depense) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.DEPENSE_MONTANT, depense);
        long insertId = database.insert(MySQLiteHelper.DEPENSE_TABLE_NAME, null, values);
        Cursor cursor = database.query(MySQLiteHelper.DEPENSE_TABLE_NAME, allColumn, MySQLiteHelper.DEPENSE_ID + " = " + insertId, null, null, null, null);
        Depenses newDepense = cursorToDepense(cursor);
        cursor.close();
        return newDepense;
    }

    

    private Depenses cursorToDepense(Cursor cursor) {
        Depenses depense = new Depenses();
        depense.setId(cursor.getLong(0));
        depense.setMontant(cursor.getFloat(1));
        return depense;
    }
}
