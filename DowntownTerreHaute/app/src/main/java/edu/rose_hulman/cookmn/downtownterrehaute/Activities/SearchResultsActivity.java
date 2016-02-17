package edu.rose_hulman.cookmn.downtownterrehaute.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.rose_hulman.cookmn.downtownterrehaute.Adapters.EstablishmentAdapter;
import edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects.Establishment;
import edu.rose_hulman.cookmn.downtownterrehaute.R;

public class SearchResultsActivity extends AppCompatActivity implements EstablishmentAdapter.Callback {

    private EstablishmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        adapter = new EstablishmentAdapter(this, this, type);
        RecyclerView recycle = (RecyclerView) findViewById(R.id.recycler_view);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        recycle.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void seeDetails(Establishment establishment) {
        Intent intent = new Intent(this,EstablishmentInformationActivity.class);
        intent.putExtra("name", establishment.getName());
        intent.putExtra("hours", establishment.getHours());
        intent.putExtra("location", establishment.getLocation());
        intent.putExtra("desc", establishment.getDescription());
        intent.putExtra("logo", establishment.getLogo());
        startActivity(intent);

    }
}
