package com.concepcion.eisen.caloriecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class result extends AppCompatActivity {

    TextView tv_bmr,tv_maintain, tv_lose1, tv_lose2, tv_gain1, tv_gain2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try {
            tv_bmr = (TextView) findViewById(R.id.tvbmr);
            tv_maintain = (TextView) findViewById(R.id.tvmaintain);
            tv_lose1 = (TextView) findViewById(R.id.tvlose1);
            tv_lose2 = (TextView) findViewById(R.id.tvlose2);
            tv_gain1 = (TextView) findViewById(R.id.tvgain1);
            tv_gain2 = (TextView) findViewById(R.id.tvgain2);

            //kunin muna yung values from mainactivity

            String input_age = getIntent().getStringExtra("age_input");
            String input_feet = getIntent().getStringExtra("feet_input");
            String input_inches = getIntent().getStringExtra("inches_input");
            String input_weight = getIntent().getStringExtra("weight_input");
            String input_gender = getIntent().getStringExtra("gender_input");
            String input_activity = getIntent().getStringExtra("activity_input");

            int age = Integer.parseInt(input_age);
            int weight = Integer.parseInt(input_weight);
            int feet = Integer.parseInt(input_feet);
            int inches = Integer.parseInt(input_inches);

            double height = getHeight(feet, inches);
            double bmr;


            if (input_gender.equals("MALE")) {
                bmr = (10 * weight + 6.25 * height - 5 * age + 5);
            } else {
                bmr = (10 * weight + 6.25 * height - 5 * age - 161);
            }

            String bmr_result = Double.toString(bmr);
            tv_bmr.setText(bmr_result);

            //GIVING VALUES TO THE ACTIVITY
            double lose1, lose2, gain1, gain2, maintain;
            switch (input_activity) {

                case "SEDENTARY":
                    maintain = bmr * 1.2;
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    tv_maintain.setText(Double.toString(maintain));
                    tv_lose1.setText(Double.toString(lose1));
                    tv_lose2.setText(Double.toString(lose2));
                    tv_gain1.setText(Double.toString(gain1));
                    tv_gain2.setText(Double.toString(gain2));
                    break;

            }
        }catch(Exception e){
            Toast.makeText(this,"Insert missing field..", Toast.LENGTH_LONG).show();
        }
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

    public double getHeight(int feet, int inches){
        //will return value in centimeter
        int temp, total_inch;
        double total_cm;
        temp = feet * 12;
        total_inch = temp + inches;

        total_cm = 2.54 * total_inch;
        return total_cm;

    }
}
