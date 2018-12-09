package com.concepcion.eisen.caloriecalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class progress extends AppCompatActivity {

    String temp = "";
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv,tv14,tv15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tv1 = findViewById(R.id.tv1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date()); // Find todays date

        String dt = "2018-12-08";  // Start date

        while(!(dt.equals(currentDate))) {


            Calendar c = Calendar.getInstance();
            try {
                c.setTime(dateFormat.parse(dt));
            } catch (ParseException e) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
            }
            c.add(Calendar.DATE, 1);  // number of days to add
            dt = dateFormat.format(c.getTime());  // dt is now the new date


            SharedPreferences pref = getSharedPreferences(dt, MODE_PRIVATE);
            String date = pref.getString("date", null);
            String height = pref.getString("height", null);
            String weight = pref.getString("weight", null);
            String bmr = pref.getString("bmr", null);

            if(date == null){

            }else {
                temp = temp + "      " + date + "           " + height + "                        " + weight + "                " + bmr + "\n";
            }
        }
        tv1.setText(temp);




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
