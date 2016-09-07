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
    private final Button button2;
    private final Context context;
    private final FoundDogViewPager foundDogViewPager;

    public FoundDogNextButtonClickListener(FoundDogViewPager foundDogViewPager, Button button, Button button2, Context context) {
        this.foundDogViewPager = foundDogViewPager;
        this.button = button;
        this.button2 = button2;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.found_dog_flow_next_button) {
            if(foundDogViewPager.getCurrentItem() < 6) {
                foundDogViewPager.setCurrentItem(foundDogViewPager.getCurrentItem() + 1);
            }

            if(foundDogViewPager.getCurrentItem() == foundDogViewPager.getAdapter().getCount() - 1){
                button.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
            }
        } else {
            Log.d("TODO", "Dod upload goes here!");
        }
    }
}
