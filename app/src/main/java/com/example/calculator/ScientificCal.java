package com.example.calculator;



import static com.example.calculator.R.id.toggle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ScientificCal extends AppCompatActivity {

    private EditText e1, e2;
    private int count = 0;
    private String expression = "";
    private String text = "";
    private Double result = 0.0;
    private DBHelper dbHelper;
    private Button mode, toggle, square, xpowy, log, sin, cos, tan, sqrt, fact;
    private int toggleMode = 1;
    private int angleMode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_cal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        mode = (Button) findViewById(R.id.mode);
        toggle = (Button) findViewById(R.id.toggle);
        square = (Button) findViewById(R.id.square);
        xpowy = (Button) findViewById(R.id.xpowy);
        log = (Button) findViewById(R.id.log);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        sqrt = (Button) findViewById(R.id.sqrt);
        fact = (Button) findViewById(R.id.factorial);

        dbHelper = new DBHelper(this);

        //tags to change the mode from degree to radian and vice versa
        mode.setTag(1);
        //tags to change the names of the buttons performing different operations
        toggle.setTag(1);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        toggleMode = (int) toggle.getTag();
        angleMode = ((int) mode.getTag());

        if (v.getId() == R.id.toggle) {
            // Change the button text if toggle button is clicked
            if (toggleMode == 1) {
                toggle.setTag(2);
                square.setText(R.string.cube);
                xpowy.setText(R.string.tenpow);
                log.setText(R.string.naturalLog);
                sin.setText(R.string.sininv);
                cos.setText(R.string.cosinv);
                tan.setText(R.string.taninv);
                sqrt.setText(R.string.cuberoot);
                fact.setText(R.string.Mod);
            } else if (toggleMode == 2) {
                toggle.setTag(3);
                square.setText(R.string.square);
                xpowy.setText(R.string.epown);
                log.setText(R.string.log);
                sin.setText(R.string.hyperbolicSine);
                cos.setText(R.string.hyperbolicCosine);
                tan.setText(R.string.hyperbolicTan);
                sqrt.setText(R.string.inverse);
                fact.setText(R.string.factorial);
            } else if (toggleMode == 3) {
                toggle.setTag(1);
                sin.setText(R.string.sin);
                cos.setText(R.string.cos);
                tan.setText(R.string.tan);
                sqrt.setText(R.string.sqrt);
                xpowy.setText(R.string.xpown);
            }
        } else if (v.getId() == R.id.mode) {
            // Change the angle property for trigonometric operations if mode button is clicked
            if (angleMode == 1) {
                mode.setTag(2);
                mode.setText(R.string.mode2);
            } else {
                mode.setTag(1);
                mode.setText(R.string.mode1);
            }
        } else if (v.getId() == R.id.num0) {
            e2.setText(e2.getText() + "0");
        } else if (v.getId() == R.id.num1) {
            e2.setText(e2.getText() + "1");
        } else if (v.getId() == R.id.num2) {
            e2.setText(e2.getText() + "2");
        } else if (v.getId() == R.id.num3) {
            e2.setText(e2.getText() + "3");
        } else if (v.getId() == R.id.num4) {
            e2.setText(e2.getText() + "4");
        } else if (v.getId() == R.id.num5) {
            e2.setText(e2.getText() + "5");
        } else if (v.getId() == R.id.num6) {
            e2.setText(e2.getText() + "6");
        } else if (v.getId() == R.id.num7) {
            e2.setText(e2.getText() + "7");
        } else if (v.getId() == R.id.num8) {
            e2.setText(e2.getText() + "8");
        } else if (v.getId() == R.id.num9) {
            e2.setText(e2.getText() + "9");
        } else if (v.getId() == R.id.pi) {
            e2.setText(e2.getText() + "pi");
        } else if (v.getId() == R.id.dot) {
            if (count == 0 && e2.length() != 0) {
                e2.setText(e2.getText() + ".");
                count++;
            }
        } else if (v.getId() == R.id.clear) {
            e1.setText("");
            e2.setText("");
            count = 0;
            expression = "";
        } else if (v.getId() == R.id.backSpace) {
            text = e2.getText().toString();
            if (text.length() > 0) {
                if (text.endsWith(".")) {
                    count = 0;
                }
                String newText = text.substring(0, text.length() - 1);
                if (text.endsWith(")")) {
                    char[] a = text.toCharArray();
                    int pos = a.length - 2;
                    int counter = 1;
                    for (int i = a.length - 2; i >= 0; i--) {
                        if (a[i] == ')') {
                            counter++;
                        } else if (a[i] == '(') {
                            counter--;
                        } else if (a[i] == '.') {
                            count = 0;
                        }
                        if (counter == 0) {
                            pos = i;
                            break;
                        }
                    }
                    newText = text.substring(0, pos);
                }
                if (newText.equals("-") || newText.endsWith("sqrt") || newText.endsWith("log") || newText.endsWith("ln")
                        || newText.endsWith("sin") || newText.endsWith("asin") || newText.endsWith("asind") || newText.endsWith("sinh")
                        || newText.endsWith("cos") || newText.endsWith("acos") || newText.endsWith("acosd") || newText.endsWith("cosh")
                        || newText.endsWith("tan") || newText.endsWith("atan") || newText.endsWith("atand") || newText.endsWith("tanh")
                        || newText.endsWith("cbrt")) {
                    newText = "";
                }
                if (newText.endsWith("^") || newText.endsWith("/")) {
                    newText = newText.substring(0, newText.length() - 1);
                } else if (newText.endsWith("pi") || newText.endsWith("e^")) {
                    newText = newText.substring(0, newText.length() - 2);
                }
                e2.setText(newText);
            }
        } else if (v.getId() == R.id.plus) {
            operationClicked("+");
        } else if (v.getId() == R.id.minus) {
            operationClicked("-");
        } else if (v.getId() == R.id.divide) {
            operationClicked("/");
        } else if (v.getId() == R.id.multiply) {
            operationClicked("*");
        } else if (v.getId() == R.id.sqrt) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (toggleMode == 1)
                    e2.setText("sqrt(" + text + ")");
                else if (toggleMode == 2)
                    e2.setText("cbrt(" + text + ")");
                else
                    e2.setText("1/(" + text + ")");
            }
        } else if (v.getId() == R.id.square) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (toggleMode == 2)
                    e2.setText("(" + text + ")^3");
                else
                    e2.setText("(" + text + ")^2");
            }
        } else if (v.getId() == R.id.xpowy) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (toggleMode == 1)
                    e2.setText("(" + text + ")^");
                else if (toggleMode == 2)
                    e2.setText("10^(" + text + ")");
                else
                    e2.setText("e^(" + text + ")");
            }
        } else if (v.getId() == R.id.log) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (toggleMode == 2)
                    e2.setText("ln(" + text + ")");
                else
                    e2.setText("log(" + text + ")");
            }
        } else if (v.getId() == R.id.factorial) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (toggleMode == 2) {
                    e1.setText("(" + text + ")%");
                    e2.setText("");
                } else {
                    String res = "";
                    try {
                        CalculateFactorial cf = new CalculateFactorial();
                        int[] arr = cf.factorial((int) Double.parseDouble(String.valueOf(new ExtendedDoubleEvaluator().evaluate(text))));
                        int res_size = cf.getRes();
                        if (res_size > 20) {
                            for (int i = res_size - 1; i >= res_size - 20; i--) {
                                if (i == res_size - 2)
                                    res += ".";
                                res += arr[i];
                            }
                            res += "E" + (res_size - 1);
                        } else {
                            for (int i = res_size - 1; i >= 0; i--) {
                                res += arr[i];
                            }
                        }
                        e2.setText(res);
                    } catch (Exception e) {
                        if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                            e2.setText("Result too big!");
                        } else
                            e2.setText("Invalid!!");
                        e.printStackTrace();
                    }
                }
            }
        } else if (v.getId() == R.id.sin) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (angleMode == 1) {
                    double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                    if (toggleMode == 1)
                        e2.setText("sin(" + angle + ")");
                    else if (toggleMode == 2)
                        e2.setText("asind(" + text + ")");
                    else
                        e2.setText("sinh(" + text + ")");
                } else {
                    if (toggleMode == 1)
                        e2.setText("sin(" + text + ")");
                    else if (toggleMode == 2)
                        e2.setText("asin(" + text + ")");
                    else
                        e2.setText("sinh(" + text + ")");
                }
            }
        } else if (v.getId() == R.id.cos) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (angleMode == 1) {
                    double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                    if (toggleMode == 1)
                        e2.setText("cos(" + angle + ")");
                    else if (toggleMode == 2)
                        e2.setText("acosd(" + text + ")");
                    else
                        e2.setText("cosh(" + text + ")");
                } else {
                    if (toggleMode == 1)
                        e2.setText("cos(" + text + ")");
                    else if (toggleMode == 2)
                        e2.setText("acos(" + text + ")");
                    else
                        e2.setText("cosh(" + text + ")");
                }
            }
        } else if (v.getId() == R.id.tan) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                if (angleMode == 1) {
                    double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                    if (toggleMode == 1)
                        e2.setText("tan(" + angle + ")");
                    else if (toggleMode == 2)
                        e2.setText("atand(" + text + ")");
                    else
                        e2.setText("tanh(" + text + ")");
                } else {
                    if (toggleMode == 1)
                        e2.setText("tan(" + text + ")");
                    else if (toggleMode == 2)
                        e2.setText("atan(" + text + ")");
                    else
                        e2.setText("tanh(" + text + ")");
                }
            }
        } else if (v.getId() == R.id.posneg) {
            if (e2.length() != 0) {
                String s = e2.getText().toString();
                char[] arr = s.toCharArray();
                if (arr[0] == '-')
                    e2.setText(s.substring(1));
                else
                    e2.setText("-" + s);
            }
        } else if (v.getId() == R.id.equal) {
            if (e2.length() != 0) {
                text = e2.getText().toString();
                expression = e1.getText().toString() + text;
            }
            e1.setText("");
            if (expression.length() == 0)
                expression = "0.0";
            try {
                result = new ExtendedDoubleEvaluator().evaluate(expression);
                if (String.valueOf(result).equals("6.123233995736766E-17")) {
                    result = 0.0;
                    e2.setText(result + "");
                } else if (String.valueOf(result).equals("1.633123935319537E16")) {
                    e2.setText("infinity");
                } else {
                    e2.setText(result + "");
                }
                if (!expression.equals("0.0"))
                    dbHelper.insert("SCIENTIFIC", expression + " = " + result);
            } catch (Exception e) {
                e2.setText("Invalid Expression");
                e1.setText("");
                expression = "";
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.openBracket) {
            e1.setText(e1.getText() + "(");
        } else if (v.getId() == R.id.closeBracket) {
            if (e2.length() != 0)
                e1.setText(e1.getText() + e2.getText().toString() + ")");
            else
                e1.setText(e1.getText() + ")");
        } else if (v.getId() == R.id.history) {
            Intent i = new Intent(this, History.class);
            i.putExtra("calcName", "SCIENTIFIC");
            startActivity(i);
        }

    }


    private void operationClicked(String op) {
        if (e2.length() != 0) {
            String text = e2.getText().toString();
            e1.setText(e1.getText() + text + op);
            e2.setText("");
            count = 0;
        } else {
            String text = e1.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                e1.setText(newText);
            }
        }
    }
}