package edu.rose_hulman.cookmn.downtownterrehaute.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.rose_hulman.cookmn.downtownterrehaute.ASyncTasks.GetImageClass;
import edu.rose_hulman.cookmn.downtownterrehaute.R;

public class EstablishmentInformationActivity extends AppCompatActivity implements GetImageClass.ImageConsumer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Intent intent = getIntent();
        TextView nameView = (TextView) findViewById(R.id.establishment_name);
        nameView.setText(intent.getStringExtra("name"));
        TextView hoursView = (TextView) findViewById(R.id.establishment_hours);
        hoursView.setText(intent.getStringExtra("hours"));
        TextView locationView = (TextView) findViewById(R.id.establishment_location);
        locationView.setText(intent.getStringExtra("location"));
        TextView descView = (TextView) findViewById(R.id.establishment_desc);
        descView.setText(intent.getStringExtra("desc"));
        GetImageClass task = new GetImageClass(this);
        task.execute(intent.getStringExtra("logo"));
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        ImageView logo = (ImageView) findViewById(R.id.establishment_logo);
        logo.setImageBitmap(bitmap);
    }
}
