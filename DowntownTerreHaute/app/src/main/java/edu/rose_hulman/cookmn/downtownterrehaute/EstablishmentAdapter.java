package edu.rose_hulman.cookmn.downtownterrehaute;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 12/14/2015.
 */
public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.ViewHolder> {
    //private final RecyclerView mRecyclerView;
    private Context mContext;
    private List<Establishment> mEstablishments;
    private static final String FIREBASE_REPO = "downtown-terre-haute";
    private static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    private static final String QUOTES_PATH = FIREBASE_URL + "/establishments";
    private Firebase establishmentRef;

    public EstablishmentAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        //mRecyclerView = recyclerView;
        mEstablishments = new ArrayList<>();
        Firebase.setAndroidContext(context);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

    }

    public EstablishmentAdapter() {
        //Need this line for the firebase
    }


    @Override
    public int getItemCount() {
        return mEstablishments.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.nameView.setText(mFoods.get(position).getName());
//        holder.imageView.setImageResource(mFoods.get(position).getResource());
//        holder.ratingBar.setRating(mFoods.get(position).getRating());
//        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                if (fromUser) {
//                    mFoods.get(position).setRating(ratingBar.getRating());
//                }
//            }
//        });
//
//        holder.nameView.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                removeItem(position);
//                return true;
//            }
//        });


    }

    public void firebasePush(Establishment establishment) {
        establishmentRef.push().setValue(establishment);
    }



    public void addItem(){


    }

    public void removeItem(int position) {
        this.notifyDataSetChanged();
        mEstablishments.remove(position);
        this.notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private TextView timeView;


        public ViewHolder(View itemView) {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
            timeView = (TextView) itemView.findViewById(R.id.time_view);
        }
    }

}
