package edu.rose_hulman.cookmn.downtownterrehaute;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class SocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button postStatus = (Button) findViewById(R.id.status_post_button);
        postStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatusPostDialog();
            }
        });
    }

    private void openStatusPostDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.dialog_post_status, null);
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.post_a_status))
                .setView(contentView)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
            dialog.show();

    }

}