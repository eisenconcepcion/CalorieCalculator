package com.concepcion.eisen.caloriecalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class result extends AppCompatActivity {

    TextView tv_bmr,tv_maintain, tv_lose1, tv_lose2, tv_gain1, tv_gain2;
    DBHelper helper;
    Cursor table;
    String cMaintain,save_input_age,save_total_height_inches, save_total_weight, save_input_activity, save_bmr_result;

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        helper = new DBHelper(this);
        table= helper.populateTable();
        table.moveToFirst();

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
            //int temp = feet + inches;

            //String total_height_inches = Integer.toString(temp);
            String total_weight = Integer.toString(weight);

            double height = getHeight(feet, inches);
            double bmr;

            save_input_age = input_age;
            save_total_height_inches = input_feet + "'" + input_inches;
            save_total_weight = total_weight;
            save_input_activity = input_activity;

            if (input_gender.equals("MALE")) {
                bmr = (10 * weight + 6.25 * height - 5 * age + 5);
            } else {
                bmr = (10 * weight + 6.25 * height - 5 * age - 161);
            }
            int int_bmr = (int) bmr;
            String bmr_result = Integer.toString(int_bmr);
            save_bmr_result = bmr_result;

            tv_bmr.setText(bmr_result);

            //GIVING VALUES TO THE ACTIVITY
            int lose1, lose2, gain1, gain2, maintain;
            double maintain_temp;
            switch (input_activity) {

                case "SEDENTARY":
                    maintain_temp = bmr * 1.2;
                    maintain = (int) (maintain_temp);
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    cMaintain = Integer.toString(maintain);
                    tv_maintain.setText(Integer.toString(maintain));
                    tv_lose1.setText(Integer.toString(lose1));
                    tv_lose2.setText(Integer.toString(lose2));
                    tv_gain1.setText(Integer.toString(gain1));
                    tv_gain2.setText(Integer.toString(gain2));
                    break;

                case "LIGHT":
                    maintain_temp = bmr * 1.375;
                    maintain = (int) maintain_temp;
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    cMaintain = Double.toString(maintain);
                    tv_maintain.setText(Integer.toString(maintain));
                    tv_lose1.setText(Integer.toString(lose1));
                    tv_lose2.setText(Integer.toString(lose2));
                    tv_gain1.setText(Integer.toString(gain1));
                    tv_gain2.setText(Integer.toString(gain2));
                    break;

                case "MODERATE":
                    maintain_temp = bmr * 1.55;
                    maintain = (int) maintain_temp;
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    cMaintain = Double.toString(maintain);
                    tv_maintain.setText(Integer.toString(maintain));
                    tv_lose1.setText(Integer.toString(lose1));
                    tv_lose2.setText(Integer.toString(lose2));
                    tv_gain1.setText(Integer.toString(gain1));
                    tv_gain2.setText(Integer.toString(gain2));
                    break;

                case "VERY ACTIVE":
                    maintain_temp = bmr * 1.725;
                    maintain = (int) maintain_temp;
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    cMaintain = Double.toString(maintain);
                    tv_maintain.setText(Integer.toString(maintain));
                    tv_lose1.setText(Integer.toString(lose1));
                    tv_lose2.setText(Integer.toString(lose2));
                    tv_gain1.setText(Integer.toString(gain1));
                    tv_gain2.setText(Integer.toString(gain2));
                    break;

                case "EXTRA ACTIVE":
                    maintain_temp = bmr * 1.9;
                    maintain = (int) maintain_temp;
                    lose1 = maintain - 500;
                    lose2 = maintain - 1000;
                    gain1 = maintain + 500;
                    gain2 = maintain + 100;

                    cMaintain = Integer.toString(maintain);
                    tv_maintain.setText(Integer.toString(maintain));
                    tv_lose1.setText(Integer.toString(lose1));
                    tv_lose2.setText(Integer.toString(lose2));
                    tv_gain1.setText(Integer.toString(gain1));
                    tv_gain2.setText(Integer.toString(gain2));
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

    public void saveResult(View v){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date()); // Find todays date



        SharedPreferences sp = getSharedPreferences(currentDate, MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();


        writer.putString("date", currentDate);
        //writer.putString("age", save_input_age);
        writer.putString("height", save_total_height_inches);
        writer.putString("weight", save_total_weight);
        //writer.putString("activity", save_input_activity);
        writer.putString("bmr", save_bmr_result);
        //writer.putString("maintain", cMaintain);
        writer.commit();
        Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();



    }
}
