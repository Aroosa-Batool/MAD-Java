package com.example.hackathon;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Time extends AppCompatActivity {

    Button getTime;
    TextView date;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        getTime = findViewById(R.id.btnGet);
        date = findViewById(R.id.tvDateTime);
        time = findViewById(R.id.tvTimeZone);

        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        char[] data = new char[5000];
                        try {
                            URL u = new URL("https://worldtimeapi.org/api/ip");
                            InputStream i = u.openStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));

                            int count =  bufferedReader.read(data);
                            String response = new String(data, 0, count);
                            Log.d("***", "****"+response);

                            JSONObject jsonObject = new JSONObject(response);
                            String dateTxt = jsonObject.getString("datetime");
                            String timezone = jsonObject.getString("timezone");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    date.setText(dateTxt);
                                    time.setText(timezone);
                                }
                            });
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }
}