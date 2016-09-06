package com.alfredobejarano.elgordo.view.dog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfredobejarano.elgordo.R;


/**
 * {@inheritDoc}
 */
public class FoundDogPageFragment extends Fragment {

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_found_dog_page, container, false);

        return rootView;
    }
}
