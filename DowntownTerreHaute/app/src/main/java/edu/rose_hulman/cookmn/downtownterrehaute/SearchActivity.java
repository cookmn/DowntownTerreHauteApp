package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button resturtantButton = (Button) findViewById(R.id.restaurants_button);
        resturtantButton.setOnClickListener(this);
        Button museumButton = (Button) findViewById(R.id.museums_button);
        museumButton.setOnClickListener(this);
        Button barButton = (Button) findViewById(R.id.bars_button);
        barButton.setOnClickListener(this);
        Button shopButton = (Button) findViewById(R.id.shops_button);
        shopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent searchResultIntent = new Intent(this, SearchResultsActivity.class);
        if(v.getId()==R.id.restaurants_button){
            searchResultIntent.putExtra("type", "restaurant");
        }else if(v.getId()==R.id.museums_button){
            searchResultIntent.putExtra("type", "museum");
        }else if(v.getId()==R.id.bars_button){
            searchResultIntent.putExtra("type", "bar");
        }else if(v.getId()==R.id.shops_button){
            searchResultIntent.putExtra("type", "shop");
        }
        startActivity(searchResultIntent);
    }
}
