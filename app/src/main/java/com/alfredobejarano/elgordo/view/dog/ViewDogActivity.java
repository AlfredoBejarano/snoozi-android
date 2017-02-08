package com.alfredobejarano.elgordo.view.dog;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.network.NetworkUtils;
import com.alfredobejarano.elgordo.presenter.ViewDogPresenter;
import com.alfredobejarano.elgordo.view.base.View;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * This class acts as the view for a lost dog page
 */
public class ViewDogActivity extends AppCompatActivity implements View {

    private TextView dogBreed;
    private TextView dogLocation;
    private TextView dogTelephone;
    private TextView dogDescription;
    private SimpleDraweeView dogPhoto;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        ArrayList<Object> params = new ArrayList<>();
        params.add(this);
        params.add(getIntent().getIntExtra("DOG_ID", 0));

        new ViewDogPresenter().attachView(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(Object data) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.view_dog_title);

        Dog dog = (Dog) data;
        Uri imageUri = Uri.parse(NetworkUtils.API_BASE_URL + String.valueOf(dog.getImage_url()));

        dogBreed = (TextView) findViewById(R.id.view_dog_breed_color);
        dogPhoto = (SimpleDraweeView) findViewById(R.id.view_dog_photo);
        dogTelephone = (TextView) findViewById(R.id.view_dog_telephone);
        dogLocation = (TextView) findViewById(R.id.view_dog_location_date);
        dogDescription = (TextView) findViewById(R.id.view_dog_description);

        dogPhoto.setImageURI(imageUri);
        dogTelephone.setText(stringValueOf(dog.getNumber()));
        dogDescription.setText(stringValueOf(dog.getDescription()));
        dogBreed.setText(String.format(getResources().getString(R.string.view_dog_color_breed), stringValueOf(dog.getBreed()), stringValueOf(dog.getColor())));
        dogLocation.setText(String.format(getResources().getString(R.string.view_dog_location_time), stringValueOf(dog.getFound_location()), stringValueOf(dog.getFound_date())));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    /**
     * This method converts a generic Object class to a String,
     * It acts as a short hand for String.valueOf(), very useful for handling
     * null values.
     * @param object - Object, the Object to be parsed as String.
     * @return String - A String version of the object received.
     */
    private String stringValueOf(Object object) {
        return String.valueOf(object);
    }
}
