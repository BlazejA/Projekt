package com.example.ilewydalem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "budzet.db";
    private static final String DATABASE_TABLE = "budzet";
    private static final String ACCOUNT_TABLE = "konto";

    public static final String ID = "ID";
    public static final String KATEGORIA = "KATEGORIA";
    public static final String WARTOSC = "WARTOSC";
    public static final String OPIS = "OPIS";
    public static final String DATA = "DATA";
    public static final String KONTO = "KONTO";

    public static final String EMAIL = "EMAIL";
    public static final String HASLO = "HASLO";


    public static final String CREATE_TABLE_MAIN =
            "CREATE TABLE " + DATABASE_TABLE + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KATEGORIA + " TEXT not null, "
                    + WARTOSC + " REAL not null, "
                    + OPIS + " TEXT, "
                    + DATA + " DATE, "
                    + KONTO + " TEXT " + ")";

    public static final String CREATE_ACC_TABLE =
            "CREATE TABLE " + ACCOUNT_TABLE + " ("
                    + EMAIL + " TEXT PRIMARY KEY, "
                    + HASLO + " TEXT not null " + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MAIN);
        sqLiteDatabase.execSQL(CREATE_ACC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertExpese(String kategoria, double wartosc, String opis, String data, String konto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KATEGORIA, kategoria);
        values.put(WARTOSC, wartosc);
        values.put(OPIS, opis);
        values.put(DATA, data);
        values.put(KONTO, konto);
        Log.d("proba", "Dodawanie" + kategoria);
        long id = db.insert(DATABASE_TABLE, null, values);
        return id != -1;
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + DATABASE_TABLE;
        db.execSQL(query);
    }

    public Cursor selectAll(String konto) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DATABASE_TABLE+" WHERE KONTO = '"+ konto+"'";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public Double selectPrice(String data,String konto) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(WARTOSC) FROM budzet WHERE DATA =? AND KONTO = '" + konto +"'", new String[]{data});
        if (c.moveToFirst()) {
            return c.getDouble(0);
        } else {
            return 0.0;
        }
    }


    public Cursor selectLast(String konto) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE "
                + KONTO + " = '" + konto + "' ORDER BY ID DESC LIMIT 1" , null);

        return c;

    }

    //Tabela z uzytkownikami

    public boolean insertAcc(String email, String haslo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(HASLO, haslo);
        long i = db.insert(ACCOUNT_TABLE, null, values);
        return i != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ACCOUNT_TABLE + " WHERE " + EMAIL + " =?", new String[]{email});
        return c.getCount() <= 0;
    }

    public boolean isExisting(String email, String haslo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ACCOUNT_TABLE + " WHERE " + EMAIL + "=? AND " + HASLO +
                " =?", new String[]{email, haslo});
        return c.getCount() > 0;
    }


}
