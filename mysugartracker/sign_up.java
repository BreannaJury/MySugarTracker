package com.example.mysugartracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class sign_up extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //save name input
        mMessageEditText = findViewById(R.id.firstname);
    }
    //hit signup to go to custom
    public void toCustom2 (View view) {
        Intent custom2Intent = new Intent(this, com.example.mysugartracker.custom.class);
        //get name input and put it in a message
        String message = "Hello " + mMessageEditText.getText().toString() + "!";
        custom2Intent.putExtra(EXTRA_MESSAGE, message);
        //start custom
        startActivity(custom2Intent);
    }
    //get name input
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";
    private EditText mMessageEditText;
}
