package com.alfredobejarano.elgordo.adddog.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.adddog.model.AddDogBody;
import com.alfredobejarano.elgordo.adddog.model.AddDogContent;
import com.alfredobejarano.elgordo.adddog.model.AddDogResponse;
import com.alfredobejarano.elgordo.adddog.presenter.AddDogPresenter;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.common.utils.PhotoUtils;
import com.alfredobejarano.elgordo.common.utils.TextUtils;
import com.alfredobejarano.elgordo.viewdog.view.ViewDogActivity;

import java.io.FileDescriptor;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.provider.MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AddDogActivity extends BaseActivity<AddDogResponse, AddDogPresenter> {
    public static final int CAMERA_SOURCE = 3;
    private static final int PHOTO_PERMISSIONS_REQUEST = 5;
    private static final int GALLERY_SOURCE = 4;
    private static final int PICK_IMAGE = 6;
    private static final String READ_ONLY_MODE = "r";
    @BindView(R.id.add_dog_form)
    ViewGroup mRoot;
    @BindView(R.id.add_dog_photo)
    ImageView mDogPhoto;
    @BindView(R.id.add_dog_breed)
    EditText mBreed;
    @BindView(R.id.add_dog_color)
    EditText mColor;
    @BindView(R.id.add_dog_description)
    EditText mDescription;
    @BindView(R.id.add_dog_number)
    EditText mNumber;
    @BindView(R.id.add_dog_place)
    EditText mPlace;
    @BindView(R.id.add_dog_date)
    EditText mDate;
    private AddDogContent mAddDogContent;

    public static Intent provideIntent(Context context) {
        return new Intent(context, AddDogActivity.class);
    }

    @Override
    protected int getLayout() {
        mAddDogContent = new AddDogContent();
        attachPresenter(new AddDogPresenter(this));
        setupToolBar(getString(R.string.add_dog_title), true);
        return R.layout.activity_add_dog;
    }

    @Override
    public void setup(AddDogResponse data) {
        startActivity(ViewDogActivity.provideIntent(this, data.getId()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Retrieves if read phone storage permission is granted.
     *
     * @return True if read external storage permission is granted.
     */
    private boolean permissionsAreGranted() {
        return ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, CAMERA) == PERMISSION_GRANTED;
    }

    /**
     * This method displays a dialog to enable permissions.
     */
    private void displayPermissionRequest() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
            displayError(R.string.permission_camera);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
            displayError(R.string.permission_storage);
        }
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PHOTO_PERMISSIONS_REQUEST);
    }

    /**
     * Defines which types of intents can be handled by the intent chooser.
     *
     * @return An intent with the defined actions.
     */
    private Intent buildChooserIntentOptions() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    private Intent buildCameraIntent() {
        return new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    }

    private Intent buildGalleryIntent() {
        return new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
    }

    public void displayIntentChooser() {
        Intent chooser = Intent.createChooser(buildChooserIntentOptions(), getString(R.string.select_picture_source))
            .putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{buildCameraIntent(), buildGalleryIntent()});
        startActivityForResult(chooser, PICK_IMAGE);
    }

    @OnClick(R.id.add_dog_photo)
    void addDogPhoto() {
        if (permissionsAreGranted()) {
            displayIntentChooser();
        } else {
            displayPermissionRequest();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                try {
                    setImage(CAMERA_SOURCE, data);
                } catch (Exception e) {
                    // If the photo doesn't came from a camera app, it will throw an exception so it must came from a gallery app.
                    setImage(GALLERY_SOURCE, data);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PHOTO_PERMISSIONS_REQUEST) {
            if ((grantResults.length > 0) && (grantResults[0] == PERMISSION_GRANTED)) {
                displayIntentChooser();
            } else {
                displayError(R.string.missing_permissions);
            }
        }
    }

    public void setImage(int requestCode, Intent data) {
        changeViewVisibility(mRoot, GONE);
        changeLoadingVisibility(VISIBLE);
        Bundle extras = data.getExtras();
        Bitmap image = requestCode == CAMERA_SOURCE ? (Bitmap) extras.get("data") : getBitmapFromUri(data.getData());
        mDogPhoto.setImageResource(R.drawable.ic_photo_placeholder);
        if (image != null) {
            mDogPhoto.setImageBitmap(image);
            mAddDogContent.setImage(PhotoUtils.imageToBase64(image));
        }
        changeLoadingVisibility(GONE);
        changeViewVisibility(mRoot, VISIBLE);
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        Bitmap image = null;
        FileDescriptor fileDescriptor;
        ParcelFileDescriptor parcelFileDescriptor;
        try {
            parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, READ_ONLY_MODE);
            fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
        } catch (Exception e) {
            // Ignore Error.
        }
        return image;
    }

    @OnClick(R.id.add_dog_send)
    void sendDog() {
        mAddDogContent.setBreed(TextUtils.safeString(mBreed.getText()));
        mAddDogContent.setColor(TextUtils.safeString(mColor.getText()));
        mAddDogContent.setDescription(TextUtils.safeString(mDescription.getText()));
        mAddDogContent.setNumber(TextUtils.safeString(mNumber.getText()));
        mAddDogContent.setFoundAddress(TextUtils.safeString(mPlace.getText()));
        mAddDogContent.setFoundDate(TextUtils.safeString(mDate.getText()));
        mPresenter.uploadDog(new AddDogBody(mAddDogContent));
        changeViewVisibility(mRoot, GONE);
        changeLoadingVisibility(VISIBLE);
    }

    @Override
    public void displayError(int stringResId) {
        super.displayError(stringResId);
        changeViewVisibility(mRoot, VISIBLE);
        changeLoadingVisibility(GONE);
    }
}
