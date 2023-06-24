package com.example.lab08;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    TextView lateral, longitudinal, vertical, illumination, pressure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lateral = findViewById(R.id.tv_lateral);
        longitudinal = findViewById(R.id.tv_longitudinal);
        vertical = findViewById(R.id.tv_vertical);
        illumination = findViewById(R.id.tv_illumination);
        pressure = findViewById(R.id.tv_pressure);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sen_acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                lateral.setText("" + sensorEvent.values[0]);
                longitudinal.setText("" + sensorEvent.values[1]);
                vertical.setText("" + sensorEvent.values[2]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
            }, sen_acc, SensorManager.SENSOR_DELAY_NORMAL);
        Sensor sen_light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                illumination.setText("" + sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        }, sen_light, SensorManager.SENSOR_DELAY_NORMAL);
        Sensor sen_pressure = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                pressure.setText("" + sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        }, sen_pressure, SensorManager.SENSOR_DELAY_NORMAL);
    }
}