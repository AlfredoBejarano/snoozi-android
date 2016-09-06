package com.alfredobejarano.elgordo.view.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jacorona on 9/6/16.
 */
public class ViewPagerAdapter extends PagerAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return 7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
