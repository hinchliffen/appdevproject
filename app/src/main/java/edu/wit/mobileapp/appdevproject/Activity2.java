package edu.wit.mobileapp.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.wit.mobileapp.appdevproject.R;

public class Activity2 extends AppCompatActivity {

    String nameInput, timeInput, dayOrNight;
    EditText inputName, inputTime;
    int amOrPm;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_2);
        Button activity2_btn = (Button)findViewById(R.id.activity2_button);
        //Creating EditText variables
        inputName = (EditText) findViewById(R.id.editTextNumber1);
        inputTime = (EditText) findViewById(R.id.editTextTime);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);

        activity2_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(Activity2.this, Activity3.class);

                Bundle bundle= new Bundle();
                //Getting text from EditText
                nameInput = inputName.getText().toString();
                timeInput = inputTime.getText().toString();
                //find the checked radiobutton in group
                amOrPm = rg.getCheckedRadioButtonId();
                //associate button with id
                rb = (RadioButton) findViewById(amOrPm);
                dayOrNight = rb.getText().toString();

                //Putting Strings in bundle
                bundle.putString("nameInput", nameInput);
                bundle.putString("timeInput", timeInput);
                bundle.putString("timeOfDay", dayOrNight);
                bundle.putString("name","Michael");
                bundle.putInt("age", 22);

                intent.putExtras(bundle);
                startActivity(intent);

                Log.v("myApp", "Activity2 button is clicked");
            }
        });



    }


}
