package com.concepcion.eisen.caloriecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
