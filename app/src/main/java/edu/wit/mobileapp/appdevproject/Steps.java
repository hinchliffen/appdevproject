package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Steps extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor countSensor;
    TextView steps, distanceOutput;
    boolean running = false;
    double distance = 0.0;
    String gender;
    int totalSteps = 0;
    double MagnitudePrevious = 0;

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
        countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event != null) {
                    float x_acceleration = event.values[0];
                    float y_acceleration = event.values[1];
                    float z_acceleration = event.values[2];

                    double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6) {
                        totalSteps++;
                    }
                    steps.setText(String.valueOf(totalSteps));
                    Log.v("myApp", "Sensor event: Step count: " + totalSteps);

                    //calculation of stride length to determine distance based on
                    // average gender stride length
                    if (gender.equals("Male")) {
                        distance = (totalSteps * 2.5) / 5280;
                        Log.v("myApp", "Distance Male: " + distance);
                    } else {
                        distance = (totalSteps * 2.2) / 5280;
                        Log.v("myApp", "Distance Female: " + distance);
                    }
                    distanceOutput.setText(distance + "miles");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.v("myApp", "Accuracy Changed: Sensor: " + sensor + " ; accuracy: " + accuracy);
            }
        };
        sensorManager.registerListener(stepDetector, countSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    //saves steps to shared preferences so data is not erased when you close the app
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.putInt("stepCount", totalSteps);
        editor.apply();
    }

    //saves steps to shared preferences so data is not erased when you close the app
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        editor.putInt("stepCount", totalSteps);
        editor.apply();
    }


    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        totalSteps = sharedPreferences.getInt("stepCount", 0);
    }
}

