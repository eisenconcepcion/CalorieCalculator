package com.concepcion.eisen.caloriecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "records.db";
    final static int VER = 1;

    final static String TABLE = "history";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VER);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE history (ID_INTEGER_PRIMARY_KEY_AUTOINCREMENT, " +
                "Age_TEXT, Height_TEXT, Weight_TEXT, Activity_TEXT, Bmr_TEXT, Cmaintain_TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String deleteTable = "DROP TABLE IF EXISTS history";

        db.execSQL(deleteTable);

    }

    public boolean insert(String age, String height, String weight, String activity, String bmr, String cmaintain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //Insert functions to manipulate: bmi, maintain

        cv.put("Age", age); //integer
        cv.put("Height", height);
        cv.put("Weight", weight);
        cv.put("Activity", activity);
        cv.put("Bmr", bmr); //integer
        cv.put("Cmaintain", cmaintain); //integer

        long isInserted = db.insert(TABLE, null, cv);

        if (isInserted == -1) {
            return false;
        } else {
            return true;
        }

    }


    public Cursor populateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM history";
        return db.rawQuery(query, null);
    }


    public boolean delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, "ID=?", new String[] {id});
        return true;
    }

}
