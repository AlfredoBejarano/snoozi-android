package com.alfredobejarano.elgordo.home.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.common.utils.PhotoUtils;
import com.alfredobejarano.elgordo.common.utils.TextUtils;
import com.alfredobejarano.elgordo.home.model.DogListResponse;
import com.alfredobejarano.elgordo.viewdog.view.ViewDogActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class DogListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.home_dog_photo)
    ImageView mDogPhoto;
    @BindView(R.id.home_dog_breed)
    TextView mDogBreed;
    @BindView(R.id.home_dog_timestamp)
    TextView mDogTimeStamp;
    private DogListResponse mDog;

    public DogListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void render(DogListResponse dog) {
        mDog = dog;
        mDogPhoto.setImageBitmap(PhotoUtils.base64ToImage(TextUtils.safeString(dog.getImage())));
        mDogBreed.setText(String.format(Locale.getDefault(), "%1$s - %2$s", TextUtils.safeString(dog.getBreed()), TextUtils.safeString(dog.getColor())));
        mDogTimeStamp.setText(TextUtils.safeString(dog.getFoundAddress()));
    }

    @OnClick(R.id.item_dog_root)
    void viewDog() {
        itemView.getContext().startActivity(ViewDogActivity.provideIntent(itemView.getContext(), mDog.getId()));
    }
}
