package mx.iteso.erickgarcia.sesion9sqlitebd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erickgarcia on 17/03/18
 */

public class AdminSQL extends SQLiteOpenHelper {
    private String create_table = "CREATE TABLE product(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "productName TEXT NOT NULL, productCategory TEXT NOT NULL, productPrice REAL NOT NULL)";

    public AdminSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
