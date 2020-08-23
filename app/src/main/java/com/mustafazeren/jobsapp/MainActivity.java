package com.mustafazeren.jobsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView timeTextView,dateTextView;
    private EditText jobTextView;
    private ListView listView;
    private Button button2;
    int idFind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.textTimeENTER);
        dateTextView = findViewById(R.id.textDateENTER);
        jobTextView = findViewById(R.id.textJobENTER);
        listView=findViewById(R.id.list_view);
        button2=findViewById(R.id.button2);

        List();


        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker;

                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTextView.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePicker.setTitle("Choose Time");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        dateTextView.setText( dayOfMonth + "/" + monthOfYear+ "/"+year);
                    }
                },year,month,day);
                datePicker.setTitle("Choose Date");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePicker);
                datePicker.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db = new DataBase(MainActivity.this);
                db.DeleteData(idFind);
                List();

                ListViewItem();
            }
        });


    }


    public void addJob(View view){
        String description=jobTextView.getText().toString();
        String date=dateTextView.getText().toString();
        String time=timeTextView.getText().toString();

        if(description.length()>0 &&description!=null && date.length()>0 && date!=null && time.length()>0 &&time!=null){
            DataBase db=new DataBase(MainActivity.this);
            db.AddValue(description,date,time);
            List();
            ListViewItem();
        }
    }


    public void List(){
        DataBase db = new DataBase(MainActivity.this);
        List<String> list = db.ListData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);
    }

    public void ListViewItem(){
        // LisView tıklama işlemi
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tıklanan verimizi alıyoruz
                String item = listView.getItemAtPosition(position).toString();
                // - Göre bölüyoruz
                String[] items = item.split(" - ");
                // id'mizi alıyoruz
                idFind = Integer.valueOf(items[0].toString());
                // Diğer verilerimizi set ediyoruz.
                jobTextView.setText(items[1].toString());
                dateTextView.setText(items[2].toString());
                timeTextView.setText(items[3].toString());
            }
        });
    }


}