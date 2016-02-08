package edu.rose_hulman.cookmn.downtownterrehaute.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.rose_hulman.cookmn.downtownterrehaute.R;

public class EventInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        TextView nameView = (TextView) findViewById(R.id.eventNameTextView);
        nameView.setText(intent.getStringExtra("name"));
        TextView dateView = (TextView) findViewById(R.id.eventDate);
        dateView.setText(intent.getStringExtra("date"));
        TextView hoursView = (TextView) findViewById(R.id.eventHours);
        hoursView.setText(intent.getStringExtra("time"));
        TextView locationView = (TextView) findViewById(R.id.eventLocation);
        locationView.setText(intent.getStringExtra("location"));
        TextView descView = (TextView) findViewById(R.id.eventDesc);
        descView.setText(intent.getStringExtra("desc"));
    }

}
