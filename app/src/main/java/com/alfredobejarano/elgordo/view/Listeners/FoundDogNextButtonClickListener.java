package com.alfredobejarano.elgordo.view.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.dog.FoundDogViewPager;

/**
 * Created by jacorona on 9/7/16.
 */
public class FoundDogNextButtonClickListener implements View.OnClickListener {
    private final Button button;
    private final Context context;
    private final FoundDogViewPager foundDogViewPager;

    public FoundDogNextButtonClickListener(FoundDogViewPager foundDogViewPager, Button button, Context context) {
        this.foundDogViewPager = foundDogViewPager;
        this.button = button;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        int pages = foundDogViewPager.getAdapter().getCount() - 1;
        int currentItem = foundDogViewPager.getCurrentItem();

        if(currentItem == pages) {
            Log.d("TODO", "Dod upload goes here!");
        } else if(currentItem == pages - 1) {
            button.setText(context.getResources().getText(R.string.ready));
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        } else {
            button.setText(context.getResources().getText(R.string.next));
            foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
        }
    }
}
