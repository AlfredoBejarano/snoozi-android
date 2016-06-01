package com.alfredobejarano.elgordo.view.Main;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.network.NetworkUtils;
import com.facebook.drawee.view.DraweeView;

import java.util.List;

/**
 * Created by jacorona on 6/1/16.
 */
public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogRecyclerViewAdapter.ViewHolder> {
    private List<Dog> dogs;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView breedAndColor;
        public TextView locationAndDate;
        public DraweeView image;
        public ViewHolder(View v) {
            super(v);
            this.image = (DraweeView) v.findViewById(R.id.dogImage);
            this.breedAndColor = (TextView) v.findViewById(R.id.BreedAndColor);
            this.locationAndDate = (TextView) v.findViewById(R.id.DogLocation);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DogRecyclerViewAdapter(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public DogRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_card_layout, parent, false);

        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = dogs.get(position).getBreed() + " " + dogs.get(position).getColor();
        String text = dogs.get(position).getFound_location() + ", " + dogs.get(position).getFound_date();
        holder.breedAndColor.setText(title);
        holder.locationAndDate.setText(text);
        holder.image.setImageURI(Uri.parse(NetworkUtils.API_BASE_URL + dogs.get(position).getThumb_url()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dogs.size();
    }
}
