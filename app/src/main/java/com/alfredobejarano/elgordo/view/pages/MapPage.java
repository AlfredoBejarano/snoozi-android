package com.alfredobejarano.elgordo.view.pages;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.location.Address;
import android.widget.Button;
import android.widget.EditText;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.google.android.gms.maps.model.Marker;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapPage extends Fragment implements Page, OnMapReadyCallback, LocationListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int pageNumber;
    private Button queryButton;
    private EditText locationQuery;
    private MapView foundDogMapView;
    private GoogleMap foundDogGoogleMap;
    private FoundDogActivity foundDogActivity;

    private OnFragmentInteractionListener mListener;

    public MapPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapPage.
     */
    public static MapPage newInstance(String param1, String param2) {
        MapPage fragment = new MapPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_page, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationQuery = (EditText) getView().findViewById(R.id.found_dog_location_query);
        queryButton = (Button) getView().findViewById(R.id.found_dog_location_query_button);

        foundDogMapView = (MapView) getView().findViewById(R.id.found_dog_map_view);
        foundDogMapView.onCreate(savedInstanceState);
        foundDogMapView.getMapAsync(this);
        MapsInitializer.initialize(this.getActivity());

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMap(getLocationFromAddress());
            }
        });

        foundDogActivity = (FoundDogActivity) getActivity();
    }

    /**
     * {@inheritDoc}
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        foundDogMapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        foundDogMapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        foundDogMapView.onDestroy();
    }

    private void updateMap(LatLng location) {
        MarkerOptions marker = new MarkerOptions()
                .position(location)
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
        foundDogGoogleMap.addMarker(marker);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 19);
        foundDogGoogleMap.animateCamera(cameraUpdate);

        locationQuery.setText(getLocationAdress(location));
    }

    private String getLocationAdress(LatLng location) {
        Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());
        String address = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
            address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    private LatLng getLocationFromAddress() {
        Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());
        LatLng location = new LatLng(19.5034338,-99.1329508);
        String query = String.valueOf(locationQuery.getText());

        if(query != null) {
            try {
                List<Address> addresses = geocoder.getFromLocationName(query, 1);
                location = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return location;
    }

    /*
     * Implements Methods
     */

    @Override
    public void setup(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void sendToActivity() {
        if(locationQuery.getText() != null) {
            ArrayList params = foundDogActivity.getParams();
            if(params.size() >= pageNumber){
                params.remove(pageNumber-1);
                params.add(pageNumber-1, getLocationAdress(getLocationFromAddress()));
                foundDogActivity.setParams(params);
            } else {
                params.add(getLocationAdress(getLocationFromAddress()));
                foundDogActivity.setParams(params);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        foundDogGoogleMap = googleMap;

        foundDogGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        foundDogGoogleMap.setMyLocationEnabled(false);

        GPSTracker tracker = new GPSTracker(this.getContext());
        if(tracker.canGetLocation()) {
            updateMap(new LatLng(tracker.getLatitude(), tracker.getLongitude()));
            foundDogGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) { /* auto-generated code */ }

                @Override
                public void onMarkerDrag(Marker marker) { /* auto-generated code */ }

                @Override
                public void onMarkerDragEnd(Marker marker) { locationQuery.setText(getLocationAdress(marker.getPosition())); }
            });
        } else {
            tracker.showSettingsAlert();
        }

        tracker.stopUsingGPS();
    }

    @Override
    public void onLocationChanged(Location location) {
        updateMap(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
