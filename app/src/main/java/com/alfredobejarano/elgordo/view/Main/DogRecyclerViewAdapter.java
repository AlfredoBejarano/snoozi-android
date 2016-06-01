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
 * <p/>
 * DogRecyclerViewAdapter
 * This class fuctions as an adapter to a RecycleView,
 * it defines a Layout and also sets how to fill it with data received from the presenter.
 */
public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogRecyclerViewAdapter.ViewHolder> {
    private List<Dog> dogs;


    public DogRecyclerViewAdapter(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public DogRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_card_layout, parent, false);

        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = dogs.get(position).getBreed() + " " + dogs.get(position).getColor();
        String text = dogs.get(position).getFound_location() + ", " + dogs.get(position).getFound_date();
        Uri imageUri = Uri.parse(NetworkUtils.API_BASE_URL + dogs.get(position).getThumb_url());

        holder.breedAndColor.setText(title);
        holder.locationAndDate.setText(text);
        holder.image.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

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
}
