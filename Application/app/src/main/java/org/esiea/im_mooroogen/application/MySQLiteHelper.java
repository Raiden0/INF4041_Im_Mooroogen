package org.esiea.im_mooroogen.application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Daren on 18/11/2015.
 */
public abstract class MySQLiteHelper extends SQLiteOpenHelper{


    public static final String DEPENSE_ID = "_id";
    public static final String DEPENSE_MONTANT = "Montant";
    public static final String DEPENSE_TABLE_NAME = "Depenses";

    private static final String DEPENSE_DATABASE_NAME = "depenses.db";
    private static final int DEPENSE_DATABASE_VERSION = 1;
    private static final String DEPENSE_TABLE_CREATE = "CREATE TABLE "+DEPENSE_TABLE_NAME+" ("+DEPENSE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DEPENSE_MONTANT+" REAL);";

    public MySQLiteHelper(Context context) {
        super(context, DEPENSE_DATABASE_NAME, null, DEPENSE_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DEPENSE_TABLE_CREATE);
    }

    public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DEPENSE_TABLE_NAME+";";

    public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.w(MySQLiteHelper.class.getName(),"Upgrading DB from version "+oldVersion+" to "+newVersion);
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }
}
