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
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 12/14/2015.
 */
public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.ViewHolder> {
    //private final RecyclerView mRecyclerView;
    private Context mContext;
    private Callback mCallback;
    private List<Establishment> mEstablishments;
    private Firebase establishmentRef;

    public EstablishmentAdapter(Callback callback, Context context, String type) {
        mContext = context;
        mCallback = callback;
        mEstablishments = new ArrayList<>();
        Firebase firebase = new Firebase(MainActivity.ESTABLISHMENTS_PATH);
        ChildEventListener childEventListener = new EstablishmentChildEventListener();
        Query query = firebase.orderByChild("type").equalTo(type);
        query.addChildEventListener(childEventListener);

    }


    @Override
    public int getItemCount() {
        return mEstablishments.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.establishment_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameView.setText(mEstablishments.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.seeDetails(mEstablishments.get(position));
            }
        });


    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private CardView cardView;


        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_view);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public interface Callback {
        void seeDetails(Establishment establishment);
    }

    private class EstablishmentChildEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Establishment establishment = dataSnapshot.getValue(Establishment.class);
            establishment.setId(dataSnapshot.getKey());
            mEstablishments.add(0, establishment);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            Establishment establishment = dataSnapshot.getValue(Establishment.class);
            for(Establishment wp: mEstablishments){
                if(key.equals(wp.getId())){
                    wp.setValues(establishment);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            for(Establishment wp: mEstablishments){
                if(key.equals(wp.getId())){
                    mEstablishments.remove(wp);
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
            Log.e("Establishments", firebaseError.getMessage());
        }
    }
}
