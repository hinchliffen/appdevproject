package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.wit.mobileapp.appdevproject.R;

public class Activity4 extends AppCompatActivity {

    TextView nameOutput, timeOutput;
    TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_4);

        Bundle bundle = this.getIntent().getExtras();

        Log.v("myApp", "This is what Activity4 has access to from the bundle: ");
        Log.v("myApp", "nameInput: " + bundle.getString("nameInput"));
        Log.v("myApp", "timeInput: " + bundle.getString("timeInput" ));
        Log.v("myApp", "timeOfDay: "+ bundle.getString("timeOfDay"));

        //timer
        timerText = (TextView) findViewById(R.id.textView2);
        timerText.setText("Not exercising yet - Timer will appear here");

        //dropdown menu for exercise time
        Spinner myspinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Activity4.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);

        //dropdown menu for time increments
        Spinner myspinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Activity4.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names2));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner2.setAdapter(myAdapter2);

        //getting information from EditText in bundle
        String uname = bundle.getString("nameInput");
        String time = bundle.getString("timeInput");
        String timeOfDay = bundle.getString("timeOfDay");

        //find place to output and print
        nameOutput = (TextView) findViewById(R.id.title4);
        timeOutput = (TextView) findViewById(R.id.timeinput);
        nameOutput.setText("Hello " + uname + "!");
        timeOutput.setText(time + " " + timeOfDay);

        //Get current time here
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        Log.v("myApp", "The current time is: " + currentTime);
        //Get startTime here, must format correctly to match currentTime's format (to properly compare)
        String startTime = time + timeOfDay;
        Log.v("myApp", "the startTime before cleaning here is: " + startTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mmaa");
        try {
            Date date = dateFormat.parse(startTime);
            startTime = dateFormat2.format(date);
        } catch (ParseException e) {
            //Invalid user input if we ever get inside here. maybe address as a lower priority
        }
        Log.v("myApp", "the startTime after cleaning here is: " + startTime);

        //Setup new thread that will automatically check if it's time to start exercising
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                String time = bundle.getString("timeInput");
                String timeOfDay = bundle.getString("timeOfDay");
                String startTime = time + timeOfDay;
                //Now clean startTime format
                Log.v("myApp", "the startTime before cleaning here is: " + startTime);
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mmaa");
                try {
                    Date date = dateFormat.parse(startTime);
                    startTime = dateFormat2.format(date);
                } catch (ParseException e) {
                    //Invalid user input if we get inside here
                }
                Log.v("myApp", "the startTime after cleaning here is: " + startTime);

                boolean isTimeToExercise = false;
                while (isTimeToExercise == false) {
                    //get current time
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa", Locale.getDefault());
                    String currentTime = sdf.format(new Date());
                    Log.v("myApp", "The current time is: " + currentTime);
                    if (startTime.equals(currentTime)) {
                        isTimeToExercise = true;
                    }
                    try { Thread.sleep(1000); }
                    catch (InterruptedException ex) { //shouldnt ever be here
                    }
                }
                runOnUiThread(new Runnable() { //This gets around an error "Only the original thread that created a view hierarchy can touch its views" somehow
                    @Override
                    public void run() {
                        nameOutput.setText("EXERCISE NOW!!!");
                        new CountDownTimer(60000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                timerText.setText("seconds til u can go on insta again: " + millisUntilFinished / 1000);
                            }
                            public void onFinish() {
                                timerText.setText("done!");
                            }
                        }.start();
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Log.v("myApp", "onCreate finished running - Running Activity4");

    }
}

