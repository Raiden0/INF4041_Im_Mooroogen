package org.esiea.im_mooroogen.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daren on 20/11/2015.
 */
public class DAOBase {
    protected final static int VERSION = 1;
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected MySQLiteHelper mHelper = null;

    public DAOBase(Context pContext) {
        this.mHelper = new MySQLiteHelper(pContext, NOM, null, VERSION) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };

    }

    public SQLiteDatabase open() {
        mDb = mHelper.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getmDb() {
        return mDb;
    }
}
