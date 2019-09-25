package com.example.mysugartracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.tabs.TabLayout;

public class Tabbed extends AppCompatActivity {

    private InputViewModel mInputViewModel;
    private LineChart mChart;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    public static final int INPUT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InputRoomDatabase db = Room.databaseBuilder(getApplicationContext(), InputRoomDatabase.class, "input_database")
                .build();

        //get preferences from settings
        androidx.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);

        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.graph));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.table));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.analysis));

        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // Setting a listener for clicks.
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }

    //go to input when fab is clicked
    public void toinput(View view) {
        Intent toinputIntent = new Intent(this, input.class);
        startActivityForResult(toinputIntent, INPUT_ACTIVITY_REQUEST_CODE);
    }

//inflates menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//handles option menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        // if settings is clicked, move to settings class
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,
                    Settings.class);
            startActivity(intent);
            return true;
        }
        // if 'call for help' clicked, move to call class
        if (id == R.id.call) {
            Intent i = new Intent(this,
                    Call.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    //getting inputs from room and putting them into the view in table
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mInputViewModel = ViewModelProviders.of(this).get(InputViewModel.class);
        //only add if there is a new input
        Log.e("result1","onActivityResult tabbed reached");
        if (requestCode == INPUT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //retrieve input from room database
            Input1 input1 = new Input1(data.getStringExtra(input.EXTRA_REPLY));
            //insert the inputs into the view model of the recyclerView
            mInputViewModel.insert(input1);

            //convert the String input1 to a double
            Double d = Double.parseDouble(input1.getValue());

            //display pop-up if blood is above target
            if (d > 8.0) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.PopupHigh,
                        Toast.LENGTH_LONG).show();
            }
            //display pop-up if blood is below target
            if (d < 4.0) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.PopupLow,
                        Toast.LENGTH_LONG).show();
            }
            //check for sms text permission
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)) {
                } else {
                    // permission is already granted
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            } else {
                if (d > 8.0) {
                    //send text for when bgl is over target
                    SmsManager sms1Manager = SmsManager.getDefault();
                    sms1Manager.sendTextMessage("smsto:+64212687924", null, "Breanna's blood glucose value is " + d + ". This is above her target range.", null, null);
                    ;
                }
                if (d < 4.0) {
                    //send text for when bgl is under target
                    SmsManager sms2Manager = SmsManager.getDefault();
                    sms2Manager.sendTextMessage("smsto:+64212687924", null, "Breanna's blood glucose value is " + d + ". This is below her target range.", null, null);
                    ;
                } else {
                    //send text for when bgl is on target
                    SmsManager sms3Manager = SmsManager.getDefault();
                    Log.e("hello", "text sent");
                    sms3Manager.sendTextMessage("smsto:+64212687924", null, "Breanna's blood glucose value is " + d + ". This is within her target range.", null, null);
                    ;
                }

            }

            //display pop-up if no input is added don't save the input
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


}
