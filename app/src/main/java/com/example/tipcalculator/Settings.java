package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
        private Button backButton;
    public static int PERCENTAGE = 15;
    public static int PERCENTAGE2 = 2;
        private int settingsDefault;
        private int settingsDefault2;
        EditText settingsInputText;
        EditText settingsInputText2;
       private CheckBox checkBox;
       private RadioGroup radioGroup;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            settingsDefault = PERCENTAGE;
            settingsDefault2 = PERCENTAGE2;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            settingsInputText = findViewById(R.id.settingsInputText);
            settingsInputText2 = findViewById(R.id.settingsInputText2);
            checkBox = findViewById(R.id.checkBox);
            radioGroup = findViewById(R.id.radioGroup);

            settingsInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    settingsDefault = Integer.parseInt( settingsInputText.getText().toString());

                    return false;
                }
            });

           settingsInputText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    settingsDefault2 = Integer.parseInt( settingsInputText2.getText().toString());

                    return false;
                }
            });

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    settingsInputText.setText("");
                    settingsInputText2.setText("1");
                    if (checkBox.isChecked())
                        settingsInputText.setHint("How many ways?");
                    else if (i == R.id.checkBox)
                        settingsInputText2.setHint("");


                }

            });

            Intent i = getIntent();
            settingsDefault = i.getIntExtra("default", 15);
            settingsDefault2 = i.getIntExtra("default2", 2);

          //if (settingsDefault == MainActivity.DEFAULT) {
           //     checkBox.setChecked(true);

            //}

            backButton = findViewById(R.id.backButton);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Settings.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }

        @Override
        public void onPause() {
            super.onPause();
            updateSharedPreferences();

        }

   // public void updateDefault() {
//        settingsDefault = sp.getInt("default2",PERCENTAGE); //
      //  settingsInputText.setText(settingsDefault);



   // }
        //putInt
        //putBoolean
        private void updateSharedPreferences() {
            SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("default", settingsDefault);
            editor.putInt("default2", settingsDefault2);
            editor.putBoolean("checkbox", checkBox.isChecked());
            editor.commit();
        }

   // @Override
  //  public void onResume() {
  //      super.onResume();
   //     updateDefault();
    }

   // }

