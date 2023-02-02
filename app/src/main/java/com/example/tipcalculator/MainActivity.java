package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    SeekBar seekBar;
    EditText inputText;
    EditText inputText2;
    TextView outputText;
    TextView outputText2;
    TextView outputText3;
    TextView seekBarLabel;
    ConstraintLayout layout;
    RadioGroup radioGroup;
    RadioButton noSplitRadio;
    RadioButton splitBillRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

       seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
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
                outputText2.setText("Total Amount"+" $" + (x * seekBar.getProgress() * 0.01 +x));
                outputText3.setText("Split Total:" +" $" + (x * seekBar.getProgress() * 0.01 +x) / (d)); // split only works if it is first selected, and given a value, then total and percentage is defined, else it crashes

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       /* inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    double value = Double.parseDouble(inputText.getText().toString());
                    String t = inputText2.getText().toString();
                    double y = Double.parseDouble(t);
                    if (noSplitRadio.isChecked()) {
                        value = value * 1;
                    } else if (splitBillRadio.isChecked()) {
                        value = value / y;
                    }
                    outputText2.setText(String.format("%.2f", value));
                }
                return false;
            }


        });
        inputText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    double value = Double.parseDouble(inputText2.getText().toString());
                    double value2 = Double.parseDouble(outputText2.getText().toString());
                    if (splitBillRadio.isChecked()) {
                        value = value / value2;
                    } else if (noSplitRadio.isChecked()) {
                        value = value * value2;
                    }
                    outputText3.setText(String.format("%.2f", value));
                }
                return false;
            }


        }); */

    }
}
