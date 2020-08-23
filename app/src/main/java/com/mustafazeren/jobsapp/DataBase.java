package com.mustafazeren.jobsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "JOBS";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_JOBS = "jobs";
    private static final String ROW_ID_ = "id";
    private static final String ROW_DESCRIPTION_ = "description";
    private static final String ROW_DATE_ = "date";
    private static final String ROW_TIME_ = "time";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_JOBS + "("
                + ROW_ID_ + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_DESCRIPTION_ + " TEXT NOT NULL, "
                + ROW_DATE_ + " TEXT NOT NULL, "
                + ROW_TIME_ + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOBS);
        onCreate(db);
    }

    public void AddValue(String description, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_DESCRIPTION_, description);
            cv.put(ROW_DATE_, date);
            cv.put(ROW_TIME_, time);
            db.insert(TABLE_JOBS, null,cv);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public List<String> ListData(){
        List<String> datas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] columns = {ROW_ID_,ROW_DESCRIPTION_,ROW_DATE_,ROW_TIME_};
            Cursor cursor = db.query(TABLE_JOBS, columns,null,null,null,null,null);
            while (cursor.moveToNext()){
                datas.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3));
            }
        }catch (Exception e){
        }
        db.close();
        return datas;
    }

    public void DeleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // id ye göre verimizi siliyoruz
            String where = ROW_ID_ + " = " + id ;
            db.delete(TABLE_JOBS,where,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }


}
