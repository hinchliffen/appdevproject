package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.wit.mobileapp.appdevproject.R;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_2);
        Button activity2_btn = (Button)findViewById(R.id.activity2_button);

        activity2_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(Activity2.this, Activity3.class);

                Bundle bundle= new Bundle();
                bundle.putString("name","Michael");
                bundle.putInt("age", 22);

                intent.putExtras(bundle);
                startActivity(intent);

                Log.v("myApp", "Activity2 button is clicked");
            }
        });



    }


}