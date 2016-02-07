package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements EventAdapter.Callback {

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        adapter = new EventAdapter(this, this);
        RecyclerView recycle = (RecyclerView) findViewById(R.id.recycler_view);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        recycle.setAdapter(adapter);


    }

    @Override
    public void seeDetails(Event event) {
        Intent intent = new Intent(this,EventInformationActivity.class);
        intent.putExtra("name", event.getName());
        SimpleDateFormat f = new SimpleDateFormat("EEE, MMM d");
        Date date = new Date(event.getDate());
        intent.putExtra("date", f.format(date));
        intent.putExtra("time", event.getTime());
        intent.putExtra("location", event.getLocation());
        intent.putExtra("desc", event.getDescription());
        intent.putExtra("logo", event.getLogo());
        startActivity(intent);

    }
}
