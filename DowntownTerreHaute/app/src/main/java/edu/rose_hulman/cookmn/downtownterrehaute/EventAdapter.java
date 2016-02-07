package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Context;
import android.support.v7.widget.CardView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dev on 12/14/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{
    private final Callback mCallback;
    //private final RecyclerView mRecyclerView;
    private Context mContext;
    private List<Event> mEvents;
    private Firebase eventRef;

    public EventAdapter(Callback callback, Context context) {
        mContext = context;
        mCallback = callback;
        mEvents = new ArrayList<>();
        eventRef = new Firebase(MainActivity.EVENTS_PATH);
        eventRef.addChildEventListener(new EventChildEventListener());
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameView.setText(mEvents.get(position).getName());
        SimpleDateFormat f = new SimpleDateFormat("EEE, MMM d");
        Date date = new Date(mEvents.get(position).getDate());
        holder.timeView.setText(f.format(date) +" @ " + mEvents.get(position).getTime());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.seeDetails(mEvents.get(position));
            }
        });


    }

    public void firebasePush(Event event) {
        eventRef.push().setValue(event);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private TextView timeView;
        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
            timeView = (TextView) itemView.findViewById(R.id.time_view);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public interface Callback {
        void seeDetails(Event event);
    }

    private class EventChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Event event = dataSnapshot.getValue(Event.class);
            event.setId(dataSnapshot.getKey());
            mEvents.add(0, event);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            Event event = dataSnapshot.getValue(Event.class);
            for(Event wp: mEvents){
                if(key.equals(wp.getId())){
                    wp.setValues(event);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for(Event wp: mEvents){
                if(key.equals(wp.getId())){
                    mEvents.remove(wp);
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
            Log.e("Events", firebaseError.getMessage());
        }
    }
}
