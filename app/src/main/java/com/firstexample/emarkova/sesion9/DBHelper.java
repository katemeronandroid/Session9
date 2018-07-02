package com.firstexample.emarkova.sesion9;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database";
    private static final int VERSION_DB = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {
        this(context, DB_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        deleteTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE NOTES ( note_id integer PRIMARY KEY, note_name text NOT NULL, create_time datetime default current_timestamp, note_text text )");
    }

    private void deleteTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS NOTES");
    }

    public ContentValues getContentValues(String name, String text, String date) {
        ContentValues values = new ContentValues();
        values.put("note_name", name);
        values.put("note_text", text);
        values.put("create_time", date);
        return values;
    }
    public ContentValues getContentValues(String name, String text) {
        ContentValues values = new ContentValues();
        values.put("note_name", name);
        values.put("note_text", text);
        return values;
    }

}
