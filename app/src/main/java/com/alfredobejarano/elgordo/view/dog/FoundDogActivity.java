package com.alfredobejarano.elgordo.view.dog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.presenter.FoundDogPresenter;
import com.alfredobejarano.elgordo.utils.GPSTracker;
import com.alfredobejarano.elgordo.utils.ImageCodec;
import com.alfredobejarano.elgordo.view.base.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class FoundDogActivity extends AppCompatActivity implements View, OnMapReadyCallback, ImageButton.OnClickListener {

    private Bundle bundle;

    private EditText breedInput;
    private EditText colorInput;
    private EditText descriptionInput;
    private EditText numberInput;

    private SupportMapFragment foundDogGoogleMap;

    private DatePicker datePicker;
    private ImageButton imageButton;


    /* Variables for building the JSON */
    private long number;

    private String breed;
    private String color;
    private String image;
    private Boolean gender;
    private String found_date;
    private String description;
    private String found_location;

    private static int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_dog);

        ArrayList<Object> params = new ArrayList<>();
        params.add(this);

        new FoundDogPresenter().attachView(params);
        setup(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.image = new ImageCodec().encodePhoto(imageBitmap);
            imageButton.setBackground(new BitmapDrawable(getResources(), imageBitmap));
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /*
    *   Implementations methods
     */

    @Override
    public void setup(Object data) {
        breedInput = (EditText) findViewById(R.id.found_dog_breed_input);
        colorInput = (EditText) findViewById(R.id.found_dog_color_input);
        numberInput = (EditText) findViewById(R.id.found_dog_number_input);
        descriptionInput = (EditText) findViewById(R.id.found_dog_description_input);

        datePicker = (DatePicker) findViewById(R.id.found_dog_calendar);

        foundDogGoogleMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.found_dog_map);
        foundDogGoogleMap.getMapAsync(this);

        imageButton = (ImageButton) findViewById(R.id.found_dog_add_photo);
        imageButton.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GPSTracker tracker = new GPSTracker(this);
        boolean canGetLocation = false;

        if(tracker.canGetLocation()) {
            LatLng location = new LatLng(tracker.getLatitude(), tracker.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(location).title(getResources().getString(R.string.your_location)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        }
    }

    @Override
    public void onClick(android.view.View view) {
        takePicture();
    }
}