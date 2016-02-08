package edu.rose_hulman.cookmn.downtownterrehaute.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects.Establishment;
import edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects.Event;
import edu.rose_hulman.cookmn.downtownterrehaute.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FIREBASE_REPO = "downtown-terre-haute";
    public static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    public static final String EVENTS_PATH = FIREBASE_URL + "/events";
    public static final String ESTABLISHMENTS_PATH = FIREBASE_URL + "/establishments";
    public static final String STATUSES_PATH = FIREBASE_URL + "/statuses";


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
        Firebase.setAndroidContext(this);
        String string_date = "14-February-2016";
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        Date d = null;
        try {
            d = f.parse(string_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long milliseconds = d.getTime();
        //firebasePushEvent(new Event("The Valentines Parade", milliseconds, "10AM-11AM", "The intersection of Wabash & 7th St.", "The Valentines Parade is a parade that showcases love", "logo"));
        //firebasePushEstablishment(new Establishment("Chuck's Comics", "12PM-6PM", "7 South 6th St., Terre Haute, IN 47807", "Chuck's Comics is a comic book store", "logo", "shop"));
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
            Intent infoIntent = new Intent(this, StatusActivity.class);
            startActivity(infoIntent);
        }
    }

    public void firebasePushEvent(Event event) {
        Firebase eventRef = new Firebase(EVENTS_PATH);
        eventRef.push().setValue(event);
    }

    public void firebasePushEstablishment(Establishment establishment) {
        Firebase establishmentRef = new Firebase(ESTABLISHMENTS_PATH);
        establishmentRef.push().setValue(establishment);
    }
}
