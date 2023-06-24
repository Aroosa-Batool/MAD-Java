package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
EditText etCity, etCountry;
TextView tvResult;
private final String url = "";
        private final String appid = "";
        DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.edit_text_City);
        etCountry = findViewById(R.id.edit_text_country);
        tvResult = findViewById(R.id.text_view_result);
    }

    public void GetWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if(city.equals(""))
        {
            tvResult.setText("City field Cannot be empty");
        }
        else
        {
            if(!country.equals(""))
            {
               tempUrl = url + "?q" + city + "," + country + "&appid" +appid;
            }
            else
            {
                tempUrl = url + "?q" +city  + "&appid=" + appid;

            }
        }
    }
}