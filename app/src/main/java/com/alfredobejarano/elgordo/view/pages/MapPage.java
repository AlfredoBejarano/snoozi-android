package com.alfredobejarano.elgordo.view.pages;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MapPage extends Fragment implements Page {
    private double latitude;
    private double longitude;
    private Geocoder geocoder;
    private EditText editText;
    private List<Address> address;
    private MapView foundDogMapView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private OnFragmentInteractionListener mListener;

    public MapPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_page, container, false);

        foundDogMapView = (MapView) view.findViewById(R.id.found_dog_location_map);
        foundDogMapView.onCreate(savedInstanceState);
        foundDogMapView.onResume();

        getLastKnowLocation();

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void getLastKnowLocation() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s) {}
        };

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 5, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            foundDogMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    MarkerOptions marker = new MarkerOptions()
                            .position(new LatLng(latitude, longitude))
                            .draggable(true)
                            .title(getAdressFromLocation(latitude, longitude));

                    googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker marker) {}

                        @Override
                        public void onMarkerDrag(Marker marker) {}

                        @Override
                        public void onMarkerDragEnd(Marker marker) {
                            latitude = marker.getPosition().latitude;
                            longitude = marker.getPosition().longitude;
                            marker.setTitle(getAdressFromLocation(latitude, longitude));
                        }
                    });

                    googleMap.addMarker(marker);

                    CameraPosition position = new CameraPosition.Builder()
                            .target(marker.getPosition())
                            .zoom(17).build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                }
            });
        } catch(SecurityException securityException) {
            securityException.printStackTrace();
        }
    }

    /**
     * This method returns an Address in human-readable format to display to the user
     * @param lat - Double,  location's latitude
     * @param lng - Double, locations longitude
     * @return String, Location's address line.
     */
    private String getAdressFromLocation(double lat, double lng) {
        geocoder = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
        try {
            address = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            return getActivity().getResources().getString(R.string.found_dog_error_address);
        }

        return address.get(0).getAddressLine(0);
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
    public void setup(int pageNumber) {

    }

    @Override
    public void sendToActivity() {

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
        void onFragmentInteraction(Uri uri);
    }
}
