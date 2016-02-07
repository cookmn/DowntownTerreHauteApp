package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookmn on 2/7/2016.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
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
        Firebase.setAndroidContext(context);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        statusRef = new Firebase(QUOTES_PATH);
        statusRef.keepSynced(true);
        statusRef.addChildEventListener(new MovieQuoteChildEventListener());
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Status status = statuses.get(position);
        holder.statusTextView.setText(status.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onEdit(status);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
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
        public void onEdit(Status status);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            statusTextView = (TextView) itemView.findViewById(R.id.quote_text);
        }
    }
}
