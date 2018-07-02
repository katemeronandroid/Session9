package com.firstexample.emarkova.sesion9;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ActivityReadWrite extends AppCompatActivity {
    private boolean write = false;
    FragmentTransaction fragmentTransaction;
    DBManager manager;
    ArrayList<String> data;
    String date;
    DataStorage storage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        write = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readwrite);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        manager = new DBManager(this);
        data = manager.getTextForRead(date);
        FragmentRead fragmentRead = new FragmentRead();
        Bundle bundle = new Bundle();
        bundle.putString("name", data.get(0));
        bundle.putString("text", data.get(1));
        fragmentRead.setArguments(bundle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameText,fragmentRead);
        fragmentTransaction.commit();
    }


    public void onWriteNote(View view) {
        if(write) {
            //переход на чтение
            Button button = (Button)findViewById(R.id.buttonWrite);
            button.setText("Редактировать");
            FragmentWrite fragment = (FragmentWrite) getSupportFragmentManager().findFragmentById(R.id.fragment_write);
            EditText name = (EditText)findViewById(R.id.editNameRead);
            Log.d("Logs", "preupdate");
            data.set(0, name.getText().toString());
            EditText text = (EditText)findViewById(R.id.editNoteRead);
            data.set(1, text.getText().toString());

            //запись в базу
            manager.updateNote(data.get(0), data.get(1), date);
            FragmentRead fragmentRead = new FragmentRead();
            Bundle bundle = new Bundle();
            bundle.putString("name", data.get(0));
            bundle.putString("text", data.get(1));
            fragmentRead.setArguments(bundle);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameText,fragmentRead);
            fragmentTransaction.commit();
            write = false;
        }
        else {
            //переход на запись
            Button button = (Button)findViewById(R.id.buttonWrite);
            button.setText("Сохранить");
            FragmentWrite fragmentWrite = new FragmentWrite();
            Bundle bundle = new Bundle();
            bundle.putString("name", data.get(0));
            bundle.putString("text", data.get(1));
            fragmentWrite.setArguments(bundle);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameText, fragmentWrite);
            fragmentTransaction.commit();
            write = true;
        }
    }

    public void onDeleteNote(View view) {
        manager.deleteNote(date);
        Intent intent = new Intent(ActivityReadWrite.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ActivityReadWrite.this, MainActivity.class);
        startActivity(intent);
    }
}
