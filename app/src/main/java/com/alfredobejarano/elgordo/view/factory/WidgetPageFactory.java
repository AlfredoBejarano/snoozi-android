package com.alfredobejarano.elgordo.view.factory;

import android.view.View;
import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.pages.WidgetPage;

/**
 * Created by Alfredo on 21/02/2017.
 */

public class WidgetPageFactory {

    public WidgetPageFactory() { /* auto-generated code */ }

    public int[] WidgetPageSettingsFactory(int pageNumber) {
        int[] settings = new int[]{ R.string.found_dog_date_question, View.VISIBLE, View.GONE };

        if(pageNumber == 7) {
            settings[0] = R.string.found_dog_photo_question;
            settings[1] = View.GONE;
            settings[2] = View.VISIBLE;
        }

        return settings;
    }
}
