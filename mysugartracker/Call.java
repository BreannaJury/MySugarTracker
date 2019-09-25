package com.example.mysugartracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Call extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        button1=(Button)findViewById(R.id.parent);
    }
    //call parent phone number on parent button click
    public void onCallButton(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+64212687924"));
        startActivity(intent);
    }
    //call starship on starship button click
    public void onCallButton2(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+6496310790"));
        startActivity(intent);
    }
    //call 111 on ambulance button click
    public void onCallButton3(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:111"));
        startActivity(intent);
    }
}
