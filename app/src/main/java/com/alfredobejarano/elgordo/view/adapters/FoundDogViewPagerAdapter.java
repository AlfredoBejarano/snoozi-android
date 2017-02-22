package com.alfredobejarano.elgordo.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.alfredobejarano.elgordo.view.pages.WidgetPage;
import com.alfredobejarano.elgordo.view.pages.RadiusOptionPage;
import com.alfredobejarano.elgordo.view.pages.TextInputPage;
import com.alfredobejarano.elgordo.view.pages.WelcomePage;

/**
 * Created by jacorona on 10/24/16.
 */

public class FoundDogViewPagerAdapter extends FragmentPagerAdapter {
    public FoundDogViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private Fragment mCurrentFragment;

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return new WelcomePage();
            case 3: return new RadiusOptionPage();
            case 6: return new WidgetPage();
            case 7: return new WidgetPage();
            default: return new TextInputPage();
        }
    }

    @Override
    public int getCount() {
        return 9;
    }
}
