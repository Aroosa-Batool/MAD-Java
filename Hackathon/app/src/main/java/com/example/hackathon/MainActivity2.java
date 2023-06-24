package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {

    Button GetTime;
    TextView Time;
    TextView Zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GetTime = findViewById(R.id.btnGetTImeScreen2);
        Time = findViewById(R.id.txtShowtime);
        GetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable R = new Runnable() {
                    @Override
                    public void run() {
                        char[] data= new char[6550];
                        try {
                            URL url = new URL("https://worldtimeapi.org/api/ip");
                            InputStream is= url.openStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            int count = br.read(data);
                            String response = new String(data, 0, count);
                            Log.d("*********", "********"+response);

                            JSONObject jo = new JSONObject(response);
                            //accessing main of the API
                            JSONObject time = jo.getJSONObject("datetime");
                            JSONObject zone = jo.getJSONObject("timezone");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Time.setText(""+time);
                                    Zone.setText(""+zone);
                                }
                            });
                        }
                        catch (Exception E){
                            E.printStackTrace();
                        }
                    }
                };
                Thread th = new Thread(R);
                th.start();
            }
        });
    }
}