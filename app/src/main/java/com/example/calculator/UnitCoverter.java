package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UnitCoverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_coverter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View v) {
        Intent i;
        int id = v.getId();

        if (id == R.id.area) {
            i = new Intent(this, UnitArea.class);
            startActivity(i);
        } else if (id == R.id.length) {
            i = new Intent(this, UnitLength.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            i = new Intent(this, UnitWeight.class);
            startActivity(i);
        } else if (id == R.id.tempearture) {
            i = new Intent(this, UnitTemperature.class);
            startActivity(i);
        }
    }
}
