package com.example.mysugartracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class custom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        //get name input from sign_up and set it as hello
        Intent intent = getIntent();
        String message = intent.getStringExtra(sign_up.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.hello);
        textView.setText(message);
    }
    //on click next go to graph page fragment
     public void tograph2 (View view) {
        Intent tograph2Intent = new Intent(this, Tabbed.class);
        startActivity(tograph2Intent);
    }

}
