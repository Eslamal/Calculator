package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button1 = findViewById(R.id.button1);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> {
            Intent i = new Intent(this, StandardCal.class);
            startActivity(i);
        });

        button.setOnClickListener(v -> {
            Intent i = new Intent(this, ScientificCal.class);
            startActivity(i);
        });

        button2.setOnClickListener(v -> {
            Intent i = new Intent(this, UnitCoverter.class);
            startActivity(i);
        });


    }

    public void onClick(View view)
    {
        Intent i;
        if(view.getId()==R.id.button1)
        {
            i=new Intent(this,StandardCal.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.button)
        {
            i=new Intent(this,ScientificCal.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.button2)
        {
            i=new Intent(this,UnitCoverter.class);
            startActivity(i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this, Setting.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}