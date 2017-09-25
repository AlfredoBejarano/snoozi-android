package com.alfredobejarano.elgordo.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class PhotoUtils {
    /**
     * Codes an image into <b>Base64</b> format.
     *
     * @param image - The image to be coded into Base64.
     * @return The image encoded into Base64 format.
     */
    public static String imageToBase64(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageAsByteArray = outputStream.toByteArray();
        return Base64.encodeToString(imageAsByteArray, Base64.NO_WRAP | Base64.URL_SAFE);
    }

    /**
     * Transform a Base64 String into a Bitmap.
     *
     * @param photoString The image in Base64.
     * @return The Image as a bitmap.
     */
    public static Bitmap base64ToImage(String photoString) {
        String imageHeader = "data:image/jpeg;base64";
        String imageHeader2 = "data:image/jpg;base64";
        String pureBase64Image = photoString;
        if (photoString.contains(imageHeader) || photoString.contains(imageHeader2)) {
            pureBase64Image = photoString.substring(photoString.indexOf(",") + 1);
        }
        byte[] decodedString = Base64.decode(pureBase64Image, Base64.NO_WRAP | Base64.URL_SAFE);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
