package com.firstexample.emarkova.sesion9;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityCreate extends AppCompatActivity {
    private static final String LOG_TAG = "LOGTAG";
    private DBManager manager;
    private EditText noteName;
    EditText noteText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        noteName = (EditText)findViewById(R.id.editTextName);
        noteText = (EditText)findViewById(R.id.editTextNote);
        manager = new DBManager(this);
    }

    public void onSaveNote(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = sdf.format(new Date());
        String name = noteName.getText().toString();
        String text = noteText.getText().toString();
        Log.d("Logs", "read data");
        manager.addNewNote(name,text,date);
        Log.d("Logs", "create note");
        startActivity(new Intent(ActivityCreate.this, MainActivity.class));
    }
}
