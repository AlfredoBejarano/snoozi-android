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
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.dog.FoundDogViewPager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TextInputPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TextInputPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextInputPage extends Fragment implements Page {

    private EditText input;
    private TextView title;
    private int pageNumber;
    private FoundDogActivity foundDogActivity;
    private FoundDogViewPager foundDogViewPager;
    private OnFragmentInteractionListener mListener;

    public TextInputPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextInputPage.
     */
    // TODO: Rename and change types and number of parameters
    public static TextInputPage newInstance() {
        TextInputPage fragment = new TextInputPage();
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
        View view = inflater.inflate(R.layout.fragment_text_input_page, container, false);

        input = (EditText) view.findViewById(R.id.found_dog_breed_input);
        title = (TextView) view.findViewById(R.id.found_dog_breed_title);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        switch(pageNumber) {
            case 1:
                title.setText(getResources().getText(R.string.found_dog_breed));
                break;
            case 2:
                title.setText(getResources().getString(R.string.found_dog_color));
                break;
            default:
                title.setText("");
                break;
        }
    }

    /**
     * This method sends the current value to the activity for building the JSON.
     */
    @Override
    public void sendToActivity() {
        if(input != null) {
            ArrayList params = foundDogActivity.getParams();
            if(params.size() >= pageNumber){
                params.remove(pageNumber-1);
                params.add(pageNumber-1, String.valueOf(input.getText()));
                foundDogActivity.setParams(params);
            } else {
                params.add(String.valueOf(input.getText()));
                foundDogActivity.setParams(params);
            }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
