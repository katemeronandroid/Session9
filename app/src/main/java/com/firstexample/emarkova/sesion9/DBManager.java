package com.firstexample.emarkova.sesion9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {
    private DBHelper helper;

    public DBManager(Context context) {
        this.helper = new DBHelper(context);
    }
    public ArrayList<String> getNameForView() {
        ArrayList<String> result = null;
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.beginTransaction();
            String [] columns = new String[] { "note_name" };
            Cursor cursor = db.query("NOTES", columns, null, null, null, null, null);
            result = parseCursor(cursor, "note_name");
            cursor.close();
            db.setTransactionSuccessful();
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
        return  result;
    }
    public ArrayList<String> getDateForView() {
        ArrayList<String> result = null;
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.beginTransaction();
            String [] columns = new String[] { "create_time" };
            Cursor cursor = db.query("NOTES", columns, null, null, null, null, null);
            result = parseCursor(cursor, "create_time");
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
        return  result;
    }
    public ArrayList<String> getTextForRead(String date) {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.beginTransaction();
            String query = "SELECT * FROM " + "NOTES" + " WHERE create_time='"+ date + "'";
            Log.d("Logs", query);
            Cursor cursor = db.rawQuery(query, null);
            result = parseOneItemCursor(cursor);
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
        return  result;
    }

    private ArrayList<String> parseOneItemCursor(Cursor cursor) {
        ArrayList<String> result = new ArrayList<String>();
        if(cursor.moveToFirst()) {
            result.add(cursor.getString(cursor.getColumnIndex("note_name")));
            result.add(cursor.getString(cursor.getColumnIndex("note_text")));
        }
        return result;
    }

    private ArrayList<String> parseCursor(Cursor cursor, String col) {
        ArrayList<String> result = new ArrayList<String>();
        if(cursor.moveToFirst()) {
            result.add(cursor.getString(cursor.getColumnIndex(col)));
            while (cursor.moveToNext()) {
                result.add(cursor.getString(cursor.getColumnIndex(col)));
            }
        }
        return result;
    }

    public void addNewNote(String name, String text, String date) {
        //helper.addNote(name, text, date);
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            ContentValues values = helper.getContentValues(name, text, date);
            db.insert("NOTES", null, values);
            db.setTransactionSuccessful();
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }
    public void updateNote(String name, String text, String date) {
        //helper.addNote(name, text, date);
        Log.d("Logs", "update");
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            ContentValues values = helper.getContentValues(name, text);
            Log.d("Logs", "update");
            db.update("NOTES", values, "create_time = ?", new String[] { date });
            db.setTransactionSuccessful();
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }
    public void deleteNote(String date) {
        //helper.addNote(name, text, date);
        Log.d("Logs", "delete");
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.delete("NOTES", "create_time = '" + date+ "'", null);
            db.setTransactionSuccessful();
        }catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if(db != null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }
}
