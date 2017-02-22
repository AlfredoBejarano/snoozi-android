package com.alfredobejarano.elgordo.view.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.adapters.FoundDogViewPagerAdapter;
import com.alfredobejarano.elgordo.view.base.Page;
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

        if(currentItem == pages) {
            Log.d("TODO", "Dod upload goes here!");
            currentPage.sendToActivity();
        } else if(currentItem == pages - 1) {
            button.setText(context.getResources().getText(R.string.ready));
            currentPage.sendToActivity();
            currentPage.setup(currentItem);
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        } else {
            button.setText(context.getResources().getText(R.string.next));
            currentPage.sendToActivity();
            currentPage.setup(currentItem);
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        }
    }
}
