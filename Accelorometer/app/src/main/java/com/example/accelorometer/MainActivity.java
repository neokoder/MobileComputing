package com.example.accelorometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";


    private SensorManager sensorManager;
    Sensor accelerometer;



    TextView xValue, yValue , zValue;
    Button button;
    boolean start;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = true;
        xValue = (TextView)findViewById(R.id.xValue);
        yValue = (TextView)findViewById(R.id.yValue);
        zValue = (TextView)findViewById(R.id.zValue);
        button = (Button) findViewById(R.id.button);

        Log.d(TAG, "onCreate: Initializing SensorServices ");

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered Accelerometer Listener ");



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Log.d(TAG, "onSensorChanged: X:" + event.values[0] + " Y:" + event.values[1]+ " Z:" +event.values[2]);

        if (start) {
            xValue.setText("X: " + event.values[0]);
            yValue.setText("Y: " + event.values[1]);
            zValue.setText("Z: " + event.values[2]);
        }

    }




    public void startStop(View view) {
        if (start)
        {
            start= false;
            button.setText("Start");

        }
        else
        {
            start= true;
            button.setText("Stop");
        }
    }
}

