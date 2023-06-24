package com.itubsce19008.lab12;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        FirebaseDatabase database =	FirebaseDatabase.getInstance();
//        DatabaseReference myRef = 	database.getReference("Lab12Database");
//        myRef.setValue("Hello World");

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log event for Button 1 clicked
                Bundle params = new Bundle();
                params.putString("button", "button1");
                firebaseAnalytics.logEvent("button_clicked_first", params);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log event for Button 2 clicked
                Bundle params = new Bundle();
                params.putString("button", "button2");
                firebaseAnalytics.logEvent("button_clicked_second", params);

            }
        });

    }
}