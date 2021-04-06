package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import edu.wit.mobileapp.appdevproject.R;

public class Activity4 extends AppCompatActivity {

    TextView nameOutput, timeOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_4);

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

        Bundle bundle = this.getIntent().getExtras();

        //getting information from EditText in bundle
        String uname = bundle.getString("nameInput");
        String time = bundle.getString("timeInput");
        String timeOfDay = bundle.getString("timeOfDay");

        //find place to output and print
        nameOutput = (TextView) findViewById(R.id.title4);
        timeOutput = (TextView) findViewById(R.id.timeinput);
        nameOutput.setText("Hello " + uname + "!");
        timeOutput.setText(time + " " + timeOfDay);

        Log.v("myApp", "Activity1 button is clicked");

    }
}

