package com.alfredobejarano.elgordo.view.dog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.presenter.VerifyFoundDogPresenter;
import com.alfredobejarano.elgordo.utils.GPSTracker;
import com.alfredobejarano.elgordo.view.base.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class FoundDogActivity extends AppCompatActivity implements View, OnMapReadyCallback {

    private EditText breedInput;
    private EditText colorInput;
    private EditText descriptionInput;
    private EditText numberInput;

    private MapView mapView;
    private GoogleMap foundDogGoogleMap;

    private RadioGroup radioGroup;
    private DatePicker datePicker;

    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_dog);

        ArrayList<Object> params = new ArrayList<>();
        params.add(this);

        mapView = (MapView) findViewById(R.id.found_dog_map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        new VerifyFoundDogPresenter().attachView(params);
    }

    @Override
    public void setup(Object data) {
        breedInput = (EditText) findViewById(R.id.found_dog_breed_input);
        colorInput = (EditText) findViewById(R.id.found_dog_color_input);
        numberInput = (EditText) findViewById(R.id.found_dog_number_input);
        descriptionInput = (EditText) findViewById(R.id.found_dog_description_input);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.foundDogGoogleMap = googleMap;

        GPSTracker gpsTracker = new GPSTracker(this);
        if(gpsTracker.canGetLocation()) {

            foundDogGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude())));

            foundDogGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()), 10));
        }
    }
}