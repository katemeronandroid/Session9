package com.firstexample.emarkova.sesion9;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivitySettings extends AppCompatActivity {
     private DataStorage storage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        storage = new DataStorage(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int color = storage.getTextColor();
        int size = storage.getTextSize();
        int style = storage.getTextStyle();
        switch (color){
            case Color.BLACK:
                ((RadioButton)findViewById(R.id.radioButton)).setChecked(true);
                break;
            case Color.GRAY:
                ((RadioButton)findViewById(R.id.radioButton2)).setChecked(true);
                break;
            case Color.BLUE:
                ((RadioButton)findViewById(R.id.radioButton3)).setChecked(true);
                break;
            default:
        }
        switch (size) {
            case 10:
                ((RadioButton) findViewById(R.id.radioButton4)).setChecked(true);
                break;
            case 14:
                ((RadioButton) findViewById(R.id.radioButton5)).setChecked(true);
                break;
            case 18:
                ((RadioButton) findViewById(R.id.radioButton6)).setChecked(true);
                break;
            default:
        }
        switch (style){
            case Typeface.NORMAL:
                ((RadioButton)findViewById(R.id.radioButton7)).setChecked(true);
                break;
            case Typeface.BOLD:
                ((RadioButton)findViewById(R.id.radioButton8)).setChecked(true);
                break;
            case Typeface.ITALIC:
                ((RadioButton)findViewById(R.id.radioButton9)).setChecked(true);
                break;
            default:
        }
    }

    public void onSaveSettings(View view) {
        int color = Color.BLACK;
        int size = 14;
        int style = Typeface.NORMAL;

        RadioGroup groupColor = findViewById(R.id.radiogroupColor);
        if (groupColor.getCheckedRadioButtonId() != -1) {
            if(((RadioButton)findViewById(R.id.radioButton)).isChecked()) {
                color = Color.BLACK;
            } else if(((RadioButton)findViewById(R.id.radioButton2)).isChecked()) {
                color = Color.GRAY;
            } else if (((RadioButton)findViewById(R.id.radioButton3)).isChecked()) {
                color = Color.BLUE;
            }
        }
        RadioGroup groupSize = findViewById(R.id.radiogroupSize);
        if (groupSize.getCheckedRadioButtonId() != -1) {
            if(((RadioButton)findViewById(R.id.radioButton4)).isChecked()) {
                size = 10;
            } else if(((RadioButton)findViewById(R.id.radioButton5)).isChecked()) {
                size = 14;
            } else if (((RadioButton)findViewById(R.id.radioButton6)).isChecked()) {
                size = 18;
            }
        }
        RadioGroup groupStyle = findViewById(R.id.radiogroupStyle);
        if (groupSize.getCheckedRadioButtonId() != -1) {
            if(((RadioButton)findViewById(R.id.radioButton7)).isChecked()) {
                style = Typeface.NORMAL;
            } else if(((RadioButton)findViewById(R.id.radioButton8)).isChecked()) {
                style = Typeface.BOLD;
            } else if (((RadioButton)findViewById(R.id.radioButton9)).isChecked()) {
                style =  Typeface.ITALIC;
            }
        }
        storage.saveUserParam(color, size, style);

        Intent intent = new Intent(ActivitySettings.this, MainActivity.class);
        startActivity(intent);
    }
}
