package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Steps extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    TextView steps, distanceOutput;
    boolean running = false;
    double distance = 0.0;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        //to get gender value for stride length
        Bundle bundle = getIntent().getExtras();
        String gender = bundle.getString("gender");

        Log.v("myApp", "This is what StepTracker has access to from the bundle: ");
        Log.v("myApp", "nameInput: " + bundle.getString("gender"));

        //find place to output
        steps = findViewById(R.id.num);
        distanceOutput = findViewById(R.id.distanceOutput);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //if you unregister the hardware will stop detecting steps
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running) {
            steps.setText(String.valueOf(event.values[0]));
            //calculation of stride length to determine distance based on
            // average gender stride length
            if (gender.equals("Male")) {
                distance = (event.values[0] * 2.5) / 5280;
            } else {
                distance = (event.values[0] * 2.2) / 5280;
            }
            distanceOutput.setText(distance + "@string/miles");
        }
    }

    //intentionally left blank
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}