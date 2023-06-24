package com.example.labfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.analytics.FirebaseAnalytics;
public class MainActivity extends AppCompatActivity {
Button spell_checker, camera;
    private FirebaseAnalytics firebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spell_checker = findViewById(R.id.btnSpell);
        camera = findViewById(R.id.btnCamera);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        spell_checker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Spell.class);
                startActivity(i);
                Bundle params = new Bundle();
                params.putString("button", "button1");
                firebaseAnalytics.logEvent("button_clicked_first", params);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraScreen.class);
                startActivity(i);
                Bundle params = new Bundle();
                params.putString("button", "button2");
                firebaseAnalytics.logEvent("button_clicked_second", params);
            }
        });
    }
}


