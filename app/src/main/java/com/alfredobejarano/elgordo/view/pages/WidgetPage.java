package com.alfredobejarano.elgordo.view.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.factory.WidgetPageFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WidgetPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class WidgetPage extends Fragment implements Page {

    private static final String BASE64_HEADER = "data:image/jpeg;base64,";

    private int pageNumber;
    private TextView title;
    private String b64Image;
    private DatePicker datePicker;
    private ImageButton addPhotoButton;
    private FoundDogActivity foundDogActivity;
    private OnFragmentInteractionListener mListener;

    public WidgetPage() {
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
        return inflater.inflate(R.layout.fragment_widget_page, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        datePicker = (DatePicker) getView().findViewById(R.id.found_dog_calendar);
        datePicker.setMaxDate(System.currentTimeMillis());

        title = (TextView) getView().findViewById(R.id.found_dog_widget_title);

        addPhotoButton = (ImageButton) getView().findViewById(R.id.found_dog_add_photo);
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

        int[] settings = new WidgetPageFactory().WidgetPageSettingsFactory(pageNumber);

        title.setText(settings[0]);
        datePicker.setVisibility(settings[1]);
        addPhotoButton.setVisibility(settings[2]);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendToActivity() {
        String data;
        if (pageNumber < 7) {
            data = getDateFromDatePicker();
        } else {
            data = BASE64_HEADER + b64Image;
            Log.d("RIZEN", data);
        }

        ArrayList params = foundDogActivity.getParams();

        if (data != null) {
            if (params.size() >= pageNumber) {
                params.remove(pageNumber - 1);
                params.add(pageNumber - 1, data);
                foundDogActivity.setParams(params);
            } else {
                params.add(data);
                foundDogActivity.setParams(params);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BitmapDrawable photo;

        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            final String picturePath = cursor.getString(columnIndex);
            cursor.close();

            b64Image = encodePhoto(BitmapFactory.decodeFile(picturePath));

            photo = new BitmapDrawable(getResources(), BitmapFactory.decodeFile(picturePath));
            addPhotoButton.setBackground(photo);
        }
    }

    private String encodePhoto(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap decodePhoto(String b64Image) {
        byte[] decodedString = Base64.decode(b64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    /**
     * This method returns the date from the DatePicker widget in a
     * readable format for the API.
     *
     * @return String, the Date
     */
    private String getDateFromDatePicker() {
        String dash = "-";
        String month = (datePicker.getMonth() < 10 ? "0" : "") + String.valueOf(datePicker.getMonth());
        String day = (datePicker.getDayOfMonth() < 10 ? "0" : "") + String.valueOf(datePicker.getDayOfMonth());

        return datePicker.getYear() + dash + month + dash + day;
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
