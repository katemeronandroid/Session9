package com.firstexample.emarkova.sesion9;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;

public class DataStorage {
    public static final String PREF_NAME = "USER_PARAM";
    private static final String TEXT_SIZE = "textsize";
    private static final String TEXT_COLOUR = "textcolor";
    private static final String TEXT_STYLE = "textstyle";
    private Context context;

    public DataStorage(Context context) {
        this.context = context;
    }

    public int getTextColor() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(TEXT_COLOUR, Color.BLACK);
    }
    public int getTextSize() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(TEXT_SIZE, 14);
    }
    public int getTextStyle() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(TEXT_STYLE, Typeface.NORMAL);
    }
    public void saveUserParam(int color, int size, int style) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(TEXT_COLOUR, color);
        editor.putInt(TEXT_SIZE, size);
        editor.putInt(TEXT_STYLE, style);
        editor.commit();
    }
    public boolean checkPref(SharedPreferences preferences){
        boolean result = false;
        if(preferences.contains(TEXT_SIZE)&&preferences.contains(TEXT_COLOUR)&&preferences.contains(TEXT_STYLE))
            result = true;
        return result;
    }
}
