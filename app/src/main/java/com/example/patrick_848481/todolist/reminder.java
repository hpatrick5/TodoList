package com.example.patrick_848481.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class reminder extends AppCompatActivity {
    Button ok;
    DatePicker date;
    EditText hours;
    EditText mins;
    Spinner ampm;
    ArrayList<String> ampmList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder2);

        ok = (Button) findViewById(R.id.button);
        date = (DatePicker) findViewById(R.id.datePicker);
        hours = (EditText) findViewById(R.id.hoursText);
        mins = (EditText) findViewById(R.id.minsText);
        ampm = (Spinner) findViewById(R.id.amPmSpinner);

        ampmList = new ArrayList<>();
        ampmList.add("AM");
        ampmList.add("PM");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ampmList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ampm.setAdapter(arrayAdapter);
        ampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    public void onClick(View v){
        int month = date.getMonth() + 1;
        int day = date.getDayOfMonth();
        int year = date.getYear();
        int hour = Integer.parseInt(hours.getText().toString());
        int min = Integer.parseInt(mins.getText().toString());
        String amPm = ampm.getSelectedItem().toString();


        SharedPreferences sharedPreferences = getSharedPreferences("LIST_FILENAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("month", month);
        editor.putInt("day", day);
        editor.putInt("year", year);
        editor.putInt("hour", hour);
        editor.putInt("min", min);
        editor.putString("dayTime", amPm);
        editor.putBoolean("firstTime", false);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }
}
