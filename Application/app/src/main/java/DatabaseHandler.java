import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daren on 29/11/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DEPENSE_KEY = "id";
    public static final String DEPENSE_NOM = "nom";
    public static final String DEPENSE_MONTANT = "montant";

    public static final String DEPENSE_TABLE_NAME = "Dépense";
    public static final String DEPENSE_TABLE_CREATE =
            "CREATE_TABLE " + DEPENSE_TABLE_NAME + " (" +
                    DEPENSE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DEPENSE_NOM + " TEXT, "+
                    DEPENSE_MONTANT + " REAL);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public static final String DEPENSE_TABLE_DROP = "DROP TABLE IF EXISTS " + DEPENSE_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DEPENSE_TABLE_DROP);
        onCreate(db);
    }
}
