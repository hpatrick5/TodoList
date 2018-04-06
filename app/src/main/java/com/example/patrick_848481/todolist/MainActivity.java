package com.example.patrick_848481.todolist;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends ListActivity {

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button save;
    EditText file;
    Button add;
    Button remind;
    EditText dateT;
    private PendingIntent pendingIntent;
    File file1;
    boolean firsttime;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file1 = new File(getFilesDir() + "myfile.dat()");

        list = new ArrayList<>();
        dateT = (EditText) findViewById(R.id.editText);
        add = (Button) findViewById(R.id.addB);
        remind = (Button) findViewById(R.id.reminderB);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        setupViewListener();

        Calendar calendar = Calendar.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("LIST_FILENAME", MODE_PRIVATE);
        int month = sharedPreferences.getInt("month", 0);
        int day = sharedPreferences.getInt("day", 0);
        int year = sharedPreferences.getInt("year", 0000);
        int hour = sharedPreferences.getInt("hour", 12);
        int min = sharedPreferences.getInt("min", 00);
        String amm = sharedPreferences.getString("dayTime", "AM");
        firsttime = sharedPreferences.getBoolean("firstTime", true);

        if(month == 0){
            month = calendar.getTime().getMonth();
        }
        if(day == 0){
            day = calendar.getTime().getDay();
        }
        if(year == 0000){
            year = calendar.getTime().getYear();
        }

        int amPm;

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        dateT.setText(month+ "/" + day + "/" + year + " at " + hour + ":" + min + " " + amm);
        if(amm == "AM"){
            amPm = Calendar.AM;
        }
        else{
            amPm = Calendar.PM;
        }
        calendar.set(Calendar.AM_PM, amPm);

        if(!firsttime) {

            Intent myIntent = new Intent(MainActivity.this, MyRecieve.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
        else{

        }


    }


    private void setupViewListener() {
        ListView listView = getListView();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.addItemText);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        add.setOnClickListener(listener);
        setListAdapter(adapter);
    }


    public void newFile(View v) {
        adapter.clear();
        adapter.notifyDataSetChanged();

    }

    public void changeActivity (View v){
        Intent intent = new Intent (this, reminder.class);
        startActivity(intent);

    }
    }

