package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button launchCalender = (Button) findViewById(R.id.calendar_of_events_button);
        launchCalender.setOnClickListener(this);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        Button shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.calendar_of_events_button){
            Intent infoIntent = new Intent(this, CalendarActivity.class);
            startActivity(infoIntent);

        }else if(v.getId() == R.id.search_button){
            Intent infoIntent = new Intent(this, SearchActivity.class);
            startActivity(infoIntent);

        }else if(v.getId() == R.id.share_button){
            Intent infoIntent = new Intent(this, SocialActivity.class);
            startActivity(infoIntent);
        }
    }
}
