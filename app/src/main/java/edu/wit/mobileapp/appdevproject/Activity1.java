package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.wit.mobileapp.appdevproject.R;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_1);
        Button activity1_btn = (Button)findViewById(R.id.activity1_button);

        //go to Activity 2
        activity1_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(Activity1.this, Activity2.class);

                Bundle bundle= new Bundle();

                startActivity(intent);
                finish();

                Log.v("myApp", "Activity1 button is clicked");
            }
        });



    }


}