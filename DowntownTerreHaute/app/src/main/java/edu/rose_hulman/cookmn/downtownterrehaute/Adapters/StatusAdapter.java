package edu.rose_hulman.cookmn.downtownterrehaute.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

import edu.rose_hulman.cookmn.downtownterrehaute.R;
import edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects.Status;

/**
 * Created by cookmn on 2/7/2016.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private Context mContext;
    private List<Status> statuses;
    private Callback mCallback;

    private static final String FIREBASE_REPO = "downtown-terre-haute";
    private static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    private static final String QUOTES_PATH = FIREBASE_URL + "/statuses";
    private Firebase statusRef;

    public StatusAdapter(Callback callback, Context context) {
        mCallback = callback;
        statuses = new ArrayList<>();
        //you need this line to use Firebase
        statusRef = new Firebase(QUOTES_PATH);
        statusRef.keepSynced(true);
        statusRef.addChildEventListener(new MovieQuoteChildEventListener());
        mContext = context;
    }

    private class MovieQuoteChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Status status = dataSnapshot.getValue(Status.class);
            status.setKey(dataSnapshot.getKey());
            statuses.add(0, status);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            Status status = dataSnapshot.getValue(Status.class);
            for(Status st : statuses) {
                if (st.getKey().equals(key)) {
                    st.setValues(status);
                    notifyItemChanged(statuses.indexOf(st));
                    break;
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for (Status s : statuses) {
                if (key.equals(s.getKey())) {
                    statuses.remove(s);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            Log.e("MQ", firebaseError.getMessage());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameView.setText(statuses.get(position).getText());
        holder.likesView.setText(statuses.get(position).getNumLikes() + " likes");
        holder.likesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status status = statuses.get(position);
                status.setNumLikes((int) status.getNumLikes() + 1);
                statusRef.child(statuses.get(position).getKey()).setValue(status);
            }
        });
        holder.flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status status = statuses.get(position);
                status.setFlagged(true);
                statusRef.child(statuses.get(position).getKey()).setValue(status);
                Toast.makeText(mContext, "Flagged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void firebasePush(Status status) {
        statusRef.push().setValue(status);
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public interface Callback {
        void seeDetails(Status status);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton flagButton;
        private ImageButton likesButton;
        private TextView nameView;
        private TextView likesView;


        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_view);
            likesView = (TextView) view.findViewById(R.id.likes_count_view);
            likesButton = (ImageButton) view.findViewById(R.id.likeButton);
            flagButton = (ImageButton) view.findViewById(R.id.flagButton);
        }
    }
}
