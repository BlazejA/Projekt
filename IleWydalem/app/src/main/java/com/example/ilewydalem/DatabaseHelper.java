package com.example.ilewydalem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "budzet.db";
    private static final String DATABASE_TABLE = "budzet";

    public static final String ID = "ID";
    public static final String KATEGORIA = "KATEGORIA";
    public static final String WARTOSC = "WARTOSC";
    public static final String OPIS = "OPIS";
    public static final String METODA = "METODA";
    public static final String DATA = "DATA";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KATEGORIA + " TEXT not null, "
                    + WARTOSC + " FLOAT not null, "
                    + OPIS + " TEXT, "
                    + METODA + " TEXT " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertExpose(String kategoria, double wartosc, String opis, String metoda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KATEGORIA, kategoria);
        values.put(WARTOSC, wartosc);
        values.put(OPIS, opis);
        values.put(METODA, metoda);
        long id = db.insert(DATABASE_TABLE, null, values);
        return id != -1;
    }

    public boolean updateExpose(int id, String kategoria, String Nowakategoria, double wartosc, String opis, String metoda) {
        ContentValues values = new ContentValues();
        values.put(KATEGORIA, kategoria);
        values.put(WARTOSC, wartosc);
        values.put(OPIS, opis);
        values.put(METODA, metoda);

        SQLiteDatabase db = getWritableDatabase();
        int updated = db.update(DATABASE_TABLE, values,
                ID + "=?",
                new String[]{Integer.toString(id)});
        db.close();

        return (updated == 1);
    }

    public boolean deleteOne(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(DATABASE_TABLE,
                ID + " =? ",
                new String[]{Integer.toString(id)});
        db.close();

        return (rowsDeleted == 1);
    }


}
