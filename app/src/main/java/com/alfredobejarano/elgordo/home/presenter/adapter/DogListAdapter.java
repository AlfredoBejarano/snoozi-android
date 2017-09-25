package com.alfredobejarano.elgordo.home.presenter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.home.model.DogListResponse;
import com.alfredobejarano.elgordo.home.view.viewholder.DogListViewHolder;

import java.util.List;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class DogListAdapter extends RecyclerView.Adapter<DogListViewHolder> {
    private List<DogListResponse> mDogs;

    public DogListAdapter(List<DogListResponse> dogs) {
        mDogs = dogs;
    }

    @Override
    public DogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DogListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false));
    }

    @Override
    public void onBindViewHolder(DogListViewHolder holder, int position) {
        holder.render(mDogs.get(position));
    }

    @Override
    public int getItemCount() {
        return mDogs == null ? 0 : mDogs.size();
    }
}
