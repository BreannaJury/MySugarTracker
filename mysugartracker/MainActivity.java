package com.example.mysugartracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//on click signup move to sign_up page
    public void signupMe (View view) {
        Intent signupIntent = new Intent(this, sign_up.class);
        startActivity(signupIntent);
    }
//on click login move to graph page fragment
    public void tograph (View view) {
        Intent tographIntent = new Intent(this, Tabbed.class);
        startActivity(tographIntent);
    }
}
