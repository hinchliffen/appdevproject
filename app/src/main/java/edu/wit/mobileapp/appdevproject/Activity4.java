package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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
    int intervalNumber = 0;
    int milisecondsIn15Minutes = 900000;
    int milisecondsIn60Minutes = 3600000;
    int milisecondsIn75Minutes = 4500000;
    String uname;
    String time = "";
    String timeOfDay = "";
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

        //dropdown menu for exercise interval time
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
        uname = bundle.getString("nameInput");
        time = bundle.getString("timeInput");
        timeOfDay = bundle.getString("timeOfDay");

        //find place to output and print
        nameOutput = (TextView) findViewById(R.id.title4);
        timeOutput = (TextView) findViewById(R.id.timeinput);
        nameOutput.setText("Hello " + uname + "!");
        timeOutput.setText(time + " " + timeOfDay);


        //Below code deals with the timer functionality
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
            boolean currentlyExercising = false;
            @Override
            public void run() {
                while (1 == 1) { //Timer shall work forever and ever
                    if (currentlyExercising == true) {
                        Log.v("myApp", "i am currently exercising, not checking or doing anything else for now");
                        try { Thread.sleep(1000); }
                        catch (InterruptedException ex) { //code should never be here
                        }
                        continue;
                    }
                    String startTime;
                    if (intervalNumber == 0) {
                        startTime = time + timeOfDay;
                    }
                    else if (intervalNumber == 1) { //i dont think this elseif block ever runs but im scared to delete it
                        startTime = time + timeOfDay;
                        SimpleDateFormat format = new SimpleDateFormat("hh:mmaa");
                        try {
                            Date date = format.parse(startTime);
                            date.setTime(date.getTime() + milisecondsIn75Minutes);
                            startTime = dateFormat.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        startTime = time + timeOfDay;
                    }
                    //Now clean startTime format
                    if (intervalNumber == 0) {
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
                    }
                    else if (intervalNumber == 2) {
                        Log.v("myApp", "the startTime before cleaning here is: " + startTime);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mmaa");
                        try {
                            Date date = dateFormat.parse(startTime);
                            date.setTime(date.getTime()+ milisecondsIn75Minutes);
                            startTime = dateFormat2.format(date);
                        } catch (ParseException e) {
                            //Invalid user input if we get inside here
                        }
                        Log.v("myApp", "the startTime after cleaning here is: " + startTime);
                    }

                    boolean isTimeToExercise = false;
                    while (isTimeToExercise == false) { //Loop to continuously check the time and check if it's time to start exercising
                        //get current time
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa", Locale.getDefault());
                        String currentTime = sdf.format(new Date());
                        Log.v("myApp", "intervalNumber is"+intervalNumber+"Checking to see if it's time to start exercising: The current time is: " + currentTime);
                        Log.v("myApp", "Checking to see if it's time to start exercising: The interval start time is: " + startTime);
                        if (startTime.equals(currentTime)) {
                            isTimeToExercise = true;
                        }
                        try { Thread.sleep(1000); }
                        catch (InterruptedException ex) { //code should never be here
                        }
                    }

                    //The below will run when it is time to start exercising
                    runOnUiThread(new Runnable() { //This gets around an error "Only the original thread that created a view hierarchy can touch its views" somehow
                        @Override
                        public void run() { //Time to exercise / run no pun intended
                            currentlyExercising = true;
                            //First check what interval user selected
                            Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
                            String selectedInterval = mySpinner.getSelectedItem().toString();

                            //Run code block depending on what user picked for their interval
                            if (selectedInterval.equals("1 x 30 minutes")) {//If user selected 1x30 interval
                                Log.v("myApp", "1x30 interval code executing");
                                nameOutput.setText("EXERCISE NOW!!!");
                                new CountDownTimer(60000+60000+60000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        timerText.setText("seconds til you can go on Insta again: " + millisUntilFinished / 1000);
                                    }
                                    public void onFinish() {
                                        timerText.setText("Done! - Not exercising yet - Timer will appear here");
                                        nameOutput.setText("Hello " + uname + "!");
                                        currentlyExercising = false;
                                    }
                                }.start();
                            }
                            else if(selectedInterval.equals("2 x 15 minutes")) { //If user selected 2x15 interval
                                if (intervalNumber == 0) {
                                    Log.v("myApp", "2x15 interval 1 code executing");
                                    nameOutput.setText("EXERCISE NOW!!!");
                                    new CountDownTimer(milisecondsIn15Minutes, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            timerText.setText("seconds til you can go on Insta again: " + millisUntilFinished / 1000);
                                        }
                                        public void onFinish() {
                                            timerText.setText("Done! Last interval in 1 hour...");
                                            nameOutput.setText("Hello " + uname + "!");
                                            currentlyExercising = false;
                                            intervalNumber = intervalNumber + 1;
                                            //add 2 mins to startTime to show when next interval is
                                            String startTime = time + timeOfDay;
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mmaa");
                                            SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mmaa");
                                            try {
                                                Date date = dateFormat.parse(startTime);
                                                date.setTime(date.getTime()+ milisecondsIn75Minutes);
                                                String newStartTime = dateFormat2.format(date);
                                                timeOutput.setText(newStartTime);
                                            } catch (ParseException e) {
                                                //Invalid user input if we get inside here
                                            }

                                        }
                                    }.start();
                                }
                                else if (intervalNumber == 2) {
                                    Log.v("myApp", "2x15 interval 2 code executing");
                                    nameOutput.setText("EXERCISE NOW!!!");
                                    new CountDownTimer(milisecondsIn15Minutes, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            timerText.setText("seconds til you can go on Insta again: " + millisUntilFinished / 1000);
                                        }
                                        public void onFinish() {
                                            timerText.setText("Done! No more intervals today");
                                            nameOutput.setText("Hello " + uname + "!");
                                            currentlyExercising = false;
                                            intervalNumber = 0;
                                            timeOutput = (TextView) findViewById(R.id.timeinput);
                                            timeOutput.setText(time + " " + timeOfDay);
                                        }
                                    }.start();
                                }


                            }

                            else if(selectedInterval.equals("3 x 10 minutes")) { //If user selected 3x10 interval
                                Log.v("myApp", "3x10 interval code executing");
                                nameOutput.setText("EXERCISE NOW!!!");
                                new CountDownTimer(60000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        timerText.setText("seconds til you can go on Insta again: " + millisUntilFinished / 1000);
                                    }
                                    public void onFinish() {
                                        timerText.setText("Done! Next interval in 1 hour...");
                                        nameOutput.setText("Hello " + uname + "!");
                                        currentlyExercising = false;
                                    }
                                }.start();

                                //1st interval done at this point

                                //First update startTime so it is 1 hour ahead of current

                                //Next, display that time to user

                                //Then, check time until it is time again to exercise

                                //When it's time, Exercise until timer is done

                                //2nd interval done at this point

                                //Then, update startTime so it is 1 hour ahead of current

                                //Next, display that time to user

                                //Then, check time until it is time again to exercise

                                //When it's time, exercise. this is the last interval. Exit back to outer while loop
                            }
                        }
                    });
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        //Get the user's selected exercise interval 1x30, 2x15 or 3x10
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        String selectedInterval = mySpinner.getSelectedItem().toString();
        Log.v("myApp", "selectedInterval is: " + selectedInterval);



        Log.v("myApp", "onCreate finished running - Running Activity4");

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        SharedPreferences pref =  getApplicationContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Name", uname);
        editor.putString("Time", time);
        editor.putString("TimeOfDay", timeOfDay);
        editor.apply();
    }

}

