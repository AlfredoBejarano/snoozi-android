package com.alfredobejarano.elgordo.view.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alfredobejarano.elgordo.view.pages.WelcomePage;

/**
 * Created by jacorona on 10/24/16.
 */

public class FoundDogViewPagerAdapter extends FragmentPagerAdapter {
    public FoundDogViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            default: return new WelcomePage();
        }
    }



    @Override
    public int getCount() {
        return 7;
    }
}
