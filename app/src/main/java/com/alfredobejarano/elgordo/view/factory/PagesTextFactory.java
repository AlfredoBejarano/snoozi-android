package com.alfredobejarano.elgordo.view.factory;

import android.text.InputType;
import android.view.View;

import com.alfredobejarano.elgordo.R;

/**
 * Created by Alfredo on 19/02/2017.
 */

public class PagesTextFactory {
    public PagesTextFactory() { /* auto-generated code */ }

    public int[] PagesTextSettingsFactory(int pageNumber) {
        int[] texts = new int[]{R.string.found_dog_breed_question, View.GONE, InputType.TYPE_CLASS_TEXT};

        switch (pageNumber) {
            case 1:
                texts[0] = R.string.found_dog_breed_question;
                break;
            case 2:
                texts[0] = R.string.found_dog_color_question;
                break;
            case 4:
                texts[0] = R.string.found_dog_description_question;
                texts[1] = View.VISIBLE;
                break;
            case 5:
                texts[0] = R.string.found_dog_phone_question;
                texts[2] = InputType.TYPE_CLASS_NUMBER;
                break;
            default:
                break;
        }

        return texts;
    }
}
