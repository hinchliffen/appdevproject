package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.wit.mobileapp.appdevproject.R;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_3);
        Button activity3_btn = (Button) findViewById(R.id.activity3_button);

        activity3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.setClass(Activity3.this, Activity4.class);

                Bundle bundle = intent.getExtras();

                intent.putExtras(bundle);
                startActivity(intent);


                Log.v("myApp", "Activity3 button is clicked");
            }
        });


    }
}
