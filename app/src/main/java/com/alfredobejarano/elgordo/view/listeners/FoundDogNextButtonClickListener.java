package com.alfredobejarano.elgordo.view.listeners;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.adapters.FoundDogViewPagerAdapter;
import com.alfredobejarano.elgordo.view.base.Page;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.dog.FoundDogViewPager;

/**
 * Created by jacorona on 9/7/16.
 */
public class FoundDogNextButtonClickListener implements View.OnClickListener {
    private final Button button;
    private final Context context;
    private final FoundDogViewPager foundDogViewPager;

    public FoundDogNextButtonClickListener(FoundDogViewPager foundDogViewPager, Button button, Context context) {
        this.button = button;
        this.context = context;
        this.foundDogViewPager = foundDogViewPager;
    }

    @Override
    public void onClick(View v) {
        int pages = foundDogViewPager.getAdapter().getCount() - 1;
        int currentItem = foundDogViewPager.getCurrentItem();

        Page currentPage = (Page) ((FoundDogViewPagerAdapter) foundDogViewPager.getAdapter()).getCurrentFragment();
        currentPage.setup(currentItem);

        /* if(currentItem == pages) {
            currentPage.sendToActivity();

            FoundDogActivity activity = (FoundDogActivity) ((Fragment) currentPage).getActivity();
            Log.d("BAMBOOZLED",String.valueOf(activity.getParams().size()));

            button.setText(context.getResources().getText(R.string.ready));
            //activity.uploadDog();
        } else if(currentItem == pages - 1) {
            currentPage.sendToActivity();
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        } else {
            button.setText(context.getResources().getText(R.string.next));
            currentPage.sendToActivity();
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        } */

        FoundDogActivity activity = (FoundDogActivity) ((Fragment) currentPage).getActivity();
        if(activity.getParams().size() != 8) {
            button.setText(context.getResources().getText(R.string.next));
            currentPage.sendToActivity();
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        } else {
            button.setText(context.getResources().getText(R.string.ready));
            activity.uploadDog();
        }
    }
}
