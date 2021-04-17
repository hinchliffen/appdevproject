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

    String nameInput, timeInput, dayOrNight, mOrF;
    EditText inputName, inputTime;
    int amOrPm, maleOrFemale;
    RadioButton rb, rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_2);
        Button activity2_btn = (Button)findViewById(R.id.activity2_button);
        //Creating EditText variables
        inputName = (EditText) findViewById(R.id.editTextNumber1);
        inputTime = (EditText) findViewById(R.id.editTextTime);
        //am or pm
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);
        //gender
        RadioGroup rg2 = findViewById(R.id.radioGroup);

        //associate button with id
        rb = findViewById(maleOrFemale);

        //Add data to the bundle and go to Activity 3
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
                maleOrFemale = rg2.getCheckedRadioButtonId();
                //associate button with id
                rb = (RadioButton) findViewById(amOrPm);
                dayOrNight = rb.getText().toString();
                //associate button with id
                rb = findViewById(maleOrFemale);
                mOrF = rb.getText().toString();

                //Putting Strings in bundle
                bundle.putString("nameInput", nameInput);
                bundle.putString("timeInput", timeInput);
                bundle.putString("timeOfDay", dayOrNight);
                bundle.putString("gender", mOrF);

                intent.putExtras(bundle);
                startActivity(intent);
                finish();

                //LogV statements to better see what data is being sent between Activities
                Log.v("myApp", "Activity2 button is clicked");
                Log.v("myApp", "This is what Activity2 bundled: ");
                Log.v("myApp", "nameInput: "+ nameInput );
                Log.v("myApp", "timeInput: "+ timeInput );
                Log.v("myApp", "dayOrNight: "+ dayOrNight );
                Log.v("myApp", "maleOrFemale: "+ mOrF );

            }
        });



    }


}
