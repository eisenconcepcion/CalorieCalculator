package com.concepcion.eisen.caloriecalculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextAge,editTextFeet,editTextInches,editTextWeight;
    CheckBox checkBoxMale,checkBoxFemale, checkBoxBmr, checkBoxSedentary,checkBoxLight,checkBoxModerate,checkBoxVery,checkBoxExtra;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);

    }

    public void process_calculate(View v){
        Intent i=null;



        editTextAge = (EditText) findViewById(R.id.etAge);
        editTextFeet = (EditText) findViewById(R.id.etFeet);
        editTextInches = (EditText) findViewById(R.id.etInches);
        editTextWeight = (EditText) findViewById(R.id.etWeight);

        checkBoxMale = (CheckBox) findViewById(R.id.cbMale);
        checkBoxFemale = (CheckBox) findViewById(R.id.cbFemale);

        //set Values
        String age = editTextAge.getText().toString();
        String feet = editTextFeet.getText().toString();
        String inches = editTextInches.getText().toString();
        String weight = editTextWeight.getText().toString();



        //set gender
        Boolean gender_pass=false;
        String gender = "gender_not_set";

        if(checkBoxFemale.isChecked() && checkBoxMale.isChecked()){
            Toast.makeText(this, "Choose only 1 gender", Toast.LENGTH_LONG).show();
        }else if((!checkBoxFemale.isChecked()) && (!checkBoxMale.isChecked())){
            Toast.makeText(this, "Choose a gender", Toast.LENGTH_LONG).show();
        }else if(checkBoxFemale.isChecked()){
            gender = "FEMALE";
            gender_pass = true;
        }else if(checkBoxMale.isChecked()){
            gender = "MALE";
            gender_pass = true;
        }

        //set Activity
        String activity = radioButton.getText().toString().toUpperCase();


        ///////////////////////////////////////Send to calculate///////////////////////////////////////
        if (v.getId() == R.id.calculatebtn && gender_pass ){
            i = new Intent(this,result.class);
            i.putExtra("age_input", age);
            i.putExtra("feet_input", feet);
            i.putExtra("inches_input", inches);
            i.putExtra("weight_input", weight);
            i.putExtra("gender_input", gender);
            i.putExtra("activity_input", activity);
            startActivity(i);
        }else{
            Toast.makeText(this, "Insert values for the missing fields", Toast.LENGTH_SHORT).show();
        }
    }



    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        }

    public void change_view(View v){
        Intent i=null;
        if (v.getId() == R.id.btnlist){
            i = new Intent(this,list.class);
            startActivity(i);
        }else if (v.getId() == R.id.btnprogress){
            i = new Intent(this,progress.class);
            startActivity(i);
        }else if (v.getId() == R.id.btncalculator){
            i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

}
