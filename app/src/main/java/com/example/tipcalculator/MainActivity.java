package com.example.tipcalculator;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT = 15;
    public static final int DEFAULT2 = 2;
    private SeekBar seekBar;
    private EditText inputText;
    private EditText inputText2;
    private TextView outputText;
    private TextView outputText2;
    private TextView outputText3;
    private TextView seekBarLabel;
    private ConstraintLayout layout;
    private RadioGroup radioGroup;
    private RadioButton noSplitRadio;
    private RadioButton splitBillRadio;
    private int settingsDefault;
    private int settingsDefault2;
    private boolean radioBoolean;
    private Button settingsButton;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsDefault = DEFAULT;
        settingsDefault2 = DEFAULT2;
        radioBoolean = false;

        seekBar = findViewById(R.id.seekBar);
        seekBarLabel = findViewById(R.id.seekBarLabel);
        layout = findViewById(R.id.layout);
        inputText = findViewById(R.id.inputText);
        inputText2 = findViewById(R.id.inputText2);
        outputText = findViewById(R.id.outputText);
        outputText2 = findViewById(R.id.outputText2);
        outputText3 = findViewById(R.id.outputText3);
        radioGroup = findViewById(R.id.radioGroup);
        splitBillRadio = findViewById(R.id.splitBillRadio);
        noSplitRadio = findViewById(R.id.noSplitRadio);
        settingsButton = findViewById(R.id.settingsButton);
        calculateButton = findViewById(R.id.calculateButton);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                inputText2.setText("");
                outputText3.setText("");
                if (i == R.id.noSplitRadio) {
                    inputText2.setHint("");
                } else if (i == R.id.splitBillRadio) {
                    inputText2.setHint("How many ways?");
                }

            }

        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Settings.class);
                i.putExtra("default", settingsDefault);
               // i.putExtra("default2", settingsDefault2);
                startActivity(i);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = inputText.getText().toString();
                    String dual = inputText2.getText().toString();
                    double x = Double.parseDouble(s);
                    double d;
                    if (dual.length() == 0)
                        d = 1;
                    else
                        d = Double.parseDouble(dual);
                    outputText.setText("Tip Amount" + " $" + (x * seekBar.getProgress() * 0.01));
                    outputText2.setText("Total Amount" + " $" + (x * seekBar.getProgress() * 0.01 + x));
                    outputText3.setText("Split Total:" + " $" + (x * seekBar.getProgress() * 0.01 + x) / (d));
                } catch (NumberFormatException ex) {
                    outputText.setText("*Please input a value into all fields*"); // defaults text when all values needed are not present
                }

            }


        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                try {
                    seekBarLabel.setText(i + "");
                    String s = inputText.getText().toString();
                    String dual = inputText2.getText().toString();
                    double x = Double.parseDouble(s);
                    double d;
                    if (dual.length() == 0)
                        d = 1;
                    else
                        d = Double.parseDouble(dual);
                    outputText.setText("Tip Amount" + " $" + (x * seekBar.getProgress() * 0.01));
                    outputText2.setText("Total Amount" + " $" + (x * seekBar.getProgress() * 0.01 + x));
                    outputText3.setText("Split Total:" + " $" + (x * seekBar.getProgress() * 0.01 + x) / (d)); // split only works if it is first selected, and given a value, then total and percentage is defined, else it crashes
                } catch  (NumberFormatException ex) {
                    outputText.setText("*Please input a value into all fields*"); // defaults text when all values needed are not present
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


    }

    public void updateDefault() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        settingsDefault = sp.getInt("default",DEFAULT); //
        seekBar.setProgress(settingsDefault);
        settingsDefault2 = sp.getInt("default2", DEFAULT2);
       // inputText2.setText(settingsDefault2);
        radioBoolean = sp.getBoolean("checkbox", false);
        splitBillRadio.setChecked(radioBoolean);
        noSplitRadio.setChecked(!radioBoolean);



    }

    @Override
    public void onResume() {
        super.onResume();
        updateDefault();
    }

}



