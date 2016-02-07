package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EstablishmentInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TextView nameView = (TextView) findViewById(R.id.establishment_name);
        nameView.setText(intent.getStringExtra("name"));
        TextView hoursView = (TextView) findViewById(R.id.establishment_hours);
        hoursView.setText(intent.getStringExtra("hours"));
        TextView locationView = (TextView) findViewById(R.id.establishment_location);
        locationView.setText(intent.getStringExtra("location"));
        TextView descView = (TextView) findViewById(R.id.establishment_desc);
        descView.setText(intent.getStringExtra("desc"));
    }

}
