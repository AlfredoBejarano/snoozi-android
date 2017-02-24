package com.alfredobejarano.elgordo.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.alfredobejarano.elgordo.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alfredo on 23/02/2017.
 */

public class LocationUtils {
    private Geocoder geocoder;

    public LocationUtils() throws IOException {}

    public String getAdressFromLatLng(LatLng location, Context context) throws IOException {
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);

        String address = addresses.get(0).getAddressLine(0);
        return address == null ? context.getResources().getString(R.string.location_not_found) : address;
    }

    public LatLng getLatLngFromAddress(String address, Context context) throws IOException {
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocationName(address, 1);
        Address location = addresses.get(0);

        return address == null ? new LatLng(19.500520, -99.131563) : new LatLng(location.getLatitude(), location.getLatitude());
    }
}
