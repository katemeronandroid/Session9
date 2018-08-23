package com.firstexample.emarkova.sesion9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

public class TryProvider extends ContentProvider {
    private static final String AUTHORITY =
            "com.firstexample.emarkova.sesion9.TryProvider";
    private static final String NOTE_TABLE = "NOTES";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + NOTE_TABLE);

    public static final int NOTEPAD = 1;
    public static final int NOTE_ID = 2;

    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, NOTE_TABLE, NOTEPAD);
        sURIMatcher.addURI(AUTHORITY, NOTE_TABLE + "/#",
                NOTE_ID);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(NOTE_TABLE);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case NOTE_ID:
                queryBuilder.appendWhere("_id" + " = "
                        + uri.getLastPathSegment());
                break;
            case NOTEPAD:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
