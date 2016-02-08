package edu.rose_hulman.cookmn.downtownterrehaute.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import edu.rose_hulman.cookmn.downtownterrehaute.Adapters.StatusAdapter;
import edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects.Status;
import edu.rose_hulman.cookmn.downtownterrehaute.R;

public class StatusActivity extends AppCompatActivity implements StatusAdapter.Callback {

    private StatusAdapter mAdapter;
    public static final String FIREBASE_REPO = "downtown-terre-haute";
    public static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    public static final String STATUSES_PATH = FIREBASE_URL + "/statuses";

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

        mAdapter = new StatusAdapter(this, this);
        RecyclerView view = (RecyclerView) findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(mAdapter);
    }

    private void openStatusPostDialog() {
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dialog_add_title);
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_post_status, null, false);
                builder.setView(view);
                final EditText editText = (EditText) view.findViewById(R.id.service);

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.firebasePush(new Status(editText.getText().toString(), "user", 0, false));
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "firebasePush");










       /* LayoutInflater inflater = getLayoutInflater();
        View contentView = inflater.inflate(R.layout.dialog_post_status, null);
        final Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.post_a_status))
                .setView(contentView)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebasePushStatus(new Status("data", "data2", 10, false));
                    }
                })
                .create();
            dialog.show();*/

    }

    public void firebasePushStatus(Status status) {
        Firebase establishmentRef = new Firebase(STATUSES_PATH);
        establishmentRef.push().setValue(status);
    }

    @Override
    public void seeDetails(Status status) {

    }
}
