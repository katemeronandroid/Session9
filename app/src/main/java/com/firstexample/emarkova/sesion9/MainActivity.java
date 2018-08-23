package com.firstexample.emarkova.sesion9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int SPACE = 25;
    private DBManager manager;
    private SharedPreferences mSettings;
    private DataStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = new DataStorage(this);
        manager = new DBManager(this);
        ArrayList<String> names = manager.getNameForView();
        ArrayList<String> dates = manager.getDateForView();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(SPACE));
        mAdapter = new MyAdapter(names, dates);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerClickListiner(this) {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                TextView mTextDate = (TextView)itemView.findViewById(R.id.fileDate);
                Intent intent = new Intent(MainActivity.this, ActivityReadWrite.class);
                intent.putExtra("date", mTextDate.getText());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSettings = getSharedPreferences(DataStorage.PREF_NAME, Context.MODE_PRIVATE);
        if(!storage.checkPref(mSettings))
        {
            int color = Color.BLACK;
            int size = 14;
            int style = Typeface.NORMAL;
            storage.saveUserParam(color, size, style);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onCreateNote(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityCreate.class);
        startActivity(intent);
    }

    public void onSettings(View view) {
        Intent intent = new Intent(MainActivity.this, ActivitySettings.class);
        startActivity(intent);
    }
}

