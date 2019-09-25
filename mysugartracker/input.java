package com.example.mysugartracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class input extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.mysugartrackersql.REPLY";
    private EditText mEditInputView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mEditInputView = findViewById(R.id.bgl_input);

        //set onClickListener to save inputs when submit is clicked
        final Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                //don't save input if no input is added
                if (TextUtils.isEmpty(mEditInputView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //put input into extra to allow it to be accessed by other activities
                    String input1 = mEditInputView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, input1);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }

//button click to open date picker
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.datepicker));
    }

    //process date picker into string messages
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string +
                "/" + year_string);
        //display chosen date
        TextView datetextView = findViewById(R.id.date_text);
        datetextView.setText(dateMessage);
    }

    //button click to open time picker
    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.timepicker));
    }

    //process time picker into string messages
    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        //display chosen time
        TextView timetextView = findViewById(R.id.time_text);
        timetextView.setText(timeMessage);
    }


    //on click next go to graph page fragment (submit input)
    public void submit (View view) {
        Intent submitIntent = new Intent(this, Tabbed.class);
        startActivity(submitIntent);
    }
}
