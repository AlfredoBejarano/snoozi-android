package com.alfredobejarano.elgordo.view.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.dog.FoundDogViewPager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RadiusOptionPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RadiusOptionPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RadiusOptionPage extends Fragment implements Page {
    private int pageNumber;
    private boolean gender = true;
    private RadioButton male, female;
    private FoundDogActivity foundDogActivity;
    private FoundDogViewPager foundDogViewPager;
    private OnFragmentInteractionListener mListener;

    public RadiusOptionPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RadiusOptionPage.
     */
    public static RadiusOptionPage newInstance() {
        RadiusOptionPage fragment = new RadiusOptionPage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foundDogActivity = (FoundDogActivity) getActivity();
        foundDogViewPager = foundDogActivity.getViewPager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radius_option_page, container, false);

        male = (RadioButton) view.findViewById(R.id.found_dog_male);
        female = (RadioButton) view.findViewById(R.id.found_dog_female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = true;
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = false;
            }
        });

        return view;
    }

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
    public void setup(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void sendToActivity() {
            ArrayList params = foundDogActivity.getParams();

            if(params.size() >= pageNumber){
                params.remove(pageNumber-1);
                params.add(pageNumber-1, gender);
                foundDogActivity.setParams(params);
            } else {
                params.add(gender);
                foundDogActivity.setParams(params);
            }
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
