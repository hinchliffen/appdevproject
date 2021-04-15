package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.wit.mobileapp.appdevproject.R;

public class Activity1 extends AppCompatActivity {

    Intent intent;
    Button activity1_btn;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_1);
        activity1_btn = (Button) findViewById(R.id.activity1_button);
        shared = getApplicationContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String name_check = shared.getString("Name", "");
        String time_check = shared.getString("Time", "");
        String tod_check = shared.getString("TimeOfDay", "");
        if (name_check != "" && time_check != "" && tod_check != "")
        {
            intent= new Intent();
            intent.setClass(Activity1.this, Activity4.class);

            Bundle bundle= new Bundle();
            bundle.putString("nameInput",name_check);
            bundle.putString("timeInput", time_check);
            bundle.putString("timeOfDay", tod_check);

            intent.putExtras(bundle);
            startActivity(intent);
        }

        activity1_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                intent= new Intent();
                intent.setClass(Activity1.this, Activity2.class);

                Bundle bundle= new Bundle();
                bundle.putString("name","Michael");
                bundle.putInt("age", 22);

                intent.putExtras(bundle);
                startActivity(intent);

                Log.v("myApp", "Activity1 button is clicked");
            }
        });



    }

}