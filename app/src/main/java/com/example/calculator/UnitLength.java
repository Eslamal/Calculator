package com.example.calculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UnitLength extends AppCompatActivity {

    private EditText e1, e2;
    private Spinner s1, s2;
    private int count1 = 0;
    private ConvertingUnits.Length ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_length);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1 = (EditText) findViewById(R.id.item1);
        e2 = (EditText) findViewById(R.id.item2);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);

        ca = new ConvertingUnits.Length();
    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.num0) {
            e1.setText(e1.getText() + "0");
        } else if (id == R.id.num1) {
            e1.setText(e1.getText() + "1");
        } else if (id == R.id.num2) {
            e1.setText(e1.getText() + "2");
        } else if (id == R.id.num3) {
            e1.setText(e1.getText() + "3");
        } else if (id == R.id.num4) {
            e1.setText(e1.getText() + "4");
        } else if (id == R.id.num5) {
            e1.setText(e1.getText() + "5");
        } else if (id == R.id.num6) {
            e1.setText(e1.getText() + "6");
        } else if (id == R.id.num7) {
            e1.setText(e1.getText() + "7");
        } else if (id == R.id.num8) {
            e1.setText(e1.getText() + "8");
        } else if (id == R.id.num9) {
            e1.setText(e1.getText() + "9");
        } else if (id == R.id.dot) {
            if (count1 == 0) {
                e1.setText(e1.getText() + ".");
                count1++;
            }
        } else if (id == R.id.clear) {
            e1.setText("");
            e2.setText("");
            count1 = 0;
        } else if (id == R.id.backSpace) {
            if (e1.length() != 0) {
                String text = e1.getText().toString();
                if (text.endsWith(".")) {
                    count1 = 0;
                }
                String newText = text.substring(0, text.length() - 1);
                e1.setText(newText);
            }
        } else if (id == R.id.equal) {
            int item1 = s1.getSelectedItemPosition();
            int item2 = s2.getSelectedItemPosition();
            double value1 = Double.parseDouble(e1.getText().toString());
            double result = evaluate(item1, item2, value1);
            e2.setText(result + "");
        }
    }

    public double evaluate(int item1, int item2, double value) {
        double temp = 0.0;

        if (item1 == item2) {
            return value;
        } else {
            // First conversion
            if (item1 == 0) {
                temp = ca.NanoToMeter(value);
            } else if (item1 == 1) {
                temp = ca.MilliToMeter(value);
            } else if (item1 == 2) {
                temp = ca.CentiToMeter(value);
            } else if (item1 == 3) {
                temp = value;
            } else if (item1 == 4) {
                temp = ca.KiloToMeter(value);
            } else if (item1 == 5) {
                temp = ca.InchToMeter(value);
            } else if (item1 == 6) {
                temp = ca.FootToMeter(value);
            } else if (item1 == 7) {
                temp = ca.YardToMeter(value);
            } else if (item1 == 8) {
                temp = ca.MileToMeter(value);
            }

            // Second conversion
            if (item2 == 0) {
                temp = ca.MeterToNano(temp);
            } else if (item2 == 1) {
                temp = ca.MeterToMilli(temp);
            } else if (item2 == 2) {
                temp = ca.MeterToCenti(temp);
            } else if (item2 == 4) {
                temp = ca.MeterToKilo(temp);
            } else if (item2 == 5) {
                temp = ca.MeterToInch(temp);
            } else if (item2 == 6) {
                temp = ca.MeterToFoot(temp);
            } else if (item2 == 7) {
                temp = ca.MeterToYard(temp);
            } else if (item2 == 8) {
                temp = ca.MeterToMile(temp);
            }

            return temp;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
