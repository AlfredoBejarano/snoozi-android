package com.alfredobejarano.elgordo.viewdog.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.common.utils.PhotoUtils;
import com.alfredobejarano.elgordo.common.utils.TextUtils;
import com.alfredobejarano.elgordo.viewdog.model.ViewDogResponse;
import com.alfredobejarano.elgordo.viewdog.presenter.ViewDogPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ViewDogActivity extends BaseActivity<ViewDogResponse, ViewDogPresenter> {
    private static final String DOG_ID_EXTRA = "dog_id";
    private static final int REQUEST_PHONE = 16;
    @BindView(R.id.view_dog_form)
    ViewGroup mRoot;
    @BindView(R.id.view_dog_photo)
    ImageView mViewPhoto;
    @BindView(R.id.view_dog_breed)
    TextView mViewBreed;
    @BindView(R.id.view_dog_color)
    TextView mViewColor;
    @BindView(R.id.view_dog_description)
    TextView mViewDescription;
    @BindView(R.id.view_dog_number)
    TextView mViewNumber;
    @BindView(R.id.view_dog_date)
    TextView mViewDate;
    @BindView(R.id.view_dog_place)
    TextView mViewPlace;
    @BindView(R.id.view_dog_photo_expanded)
    ImageView mPhotoExpanded;
    private ViewDogResponse mDog;

    public static Intent provideIntent(Context context, Integer dogId) {
        return new Intent(context, ViewDogActivity.class).putExtra(DOG_ID_EXTRA, dogId);
    }

    @Override
    protected int getLayout() {
        attachPresenter(new ViewDogPresenter(this));
        setupToolBar(getString(R.string.app_name), true);
        return R.layout.activity_view_dog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.retrieveDog(getIntent() == null ? 0 : getIntent().getIntExtra(DOG_ID_EXTRA, 0));
    }

    @Override
    public void setup(ViewDogResponse data) {
        mDog = data;
        mViewBreed.setText(TextUtils.safeString(data.getBreed()));
        mViewColor.setText(TextUtils.safeString(data.getColor()));
        mViewNumber.setText(TextUtils.safeString(data.getNumber()));
        mViewDate.setText(TextUtils.safeString(data.getFoundDate()));
        mViewPlace.setText(TextUtils.safeString(data.getFoundLocation()));
        mViewDescription.setText(TextUtils.safeString(data.getDescription()));
        mViewPhoto.setImageBitmap(PhotoUtils.base64ToImage(TextUtils.safeString(data.getImage())));
        mPhotoExpanded.setImageBitmap(PhotoUtils.base64ToImage(TextUtils.safeString(data.getImage())));
        changeLoadingVisibility(GONE);
        changeViewVisibility(mRoot, VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.view_dog_photo)
    void zoomPhoto() {
        changeViewVisibility(mPhotoExpanded, VISIBLE);
    }

    @OnClick(R.id.view_dog_photo_expanded)
    void unZoomPhoto() {
        changeViewVisibility(mPhotoExpanded, GONE);
    }

    @OnClick(R.id.view_dog_place)
    void showMap() {
        openAddressInMap();
    }

    @OnClick(R.id.view_dog_number)
    void callNumber() {
        if (ContextCompat.checkSelfPermission(this, CALL_PHONE) == PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(String.format("tel:%1$s", mDog.getNumber())));
            startActivity(callIntent);
        } else {
            displayPermissionRequest();
        }
    }

    private void displayPermissionRequest() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, CALL_PHONE)) {
            displayError(R.string.call_phone);
        }
        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, REQUEST_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PHONE) {
            if ((grantResults.length > 0) && (grantResults[0] == PERMISSION_GRANTED)) {
                callNumber();
            } else {
                mViewNumber.setEnabled(false);
                displayError(R.string.call_phone);
            }
        }
    }

    private void openAddressInMap() {
        try {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + mDog.getFoundLocation());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } catch (Exception e) {
            displayError(R.string.no_map_app_found);
        }
    }
}
