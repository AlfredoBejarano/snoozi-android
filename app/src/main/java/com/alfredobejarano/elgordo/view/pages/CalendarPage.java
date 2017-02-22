package com.alfredobejarano.elgordo.view.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CalendarPage extends Fragment implements Page {

    private int pageNumber;
    private DatePicker datePicker;
    private FoundDogActivity foundDogActivity;
    private OnFragmentInteractionListener mListener;

    public CalendarPage() {
        // Required empty public constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foundDogActivity = (FoundDogActivity) getActivity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_page, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datePicker = (DatePicker) getView().findViewById(R.id.found_dog_calendar);
        datePicker.setMaxDate(System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendToActivity() {
        String date = getDateFromDatePicker();

        if(date != null) {
            ArrayList params = foundDogActivity.getParams();
            if(params.size() >= pageNumber){
                params.remove(pageNumber-1);
                params.add(pageNumber-1, date);
                foundDogActivity.setParams(params);
            } else {
                params.add(date);
                foundDogActivity.setParams(params);
            }
        }
    }

    /**
     * This
     * @return
     */
    private String getDateFromDatePicker() {
        String dash = "-";
        String month = (datePicker.getMonth() < 10 ? "0" : "") + String.valueOf(datePicker.getMonth());
        String day = (datePicker.getDayOfMonth() < 10 ? "0" : "") + String.valueOf(datePicker.getDayOfMonth());

        return datePicker.getYear()+ dash + month + dash + day;
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
