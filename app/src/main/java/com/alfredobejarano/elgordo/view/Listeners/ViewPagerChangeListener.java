package com.alfredobejarano.elgordo.view.listeners;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alfredobejarano.elgordo.R;

/**
 * Created by jacorona on 9/6/16.
 */
public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {

    private int page;
    private int dotsCount;
    private ImageView[] dots;
    private Activity activity;
    private LinearLayout pageIndicator;

    public ViewPagerChangeListener(LinearLayout pageIndicator, Activity activity, ViewPager viewPager) {
        this.activity = activity;

        if(dots == null) {
            dotsCount =viewPager.getAdapter().getCount();
            dots = new ImageView[dotsCount];

            for (int i = 0; i < dotsCount; i++) {
                dots[i] = new ImageView(activity.getApplicationContext());
                dots[i].setImageDrawable(activity.getResources().getDrawable(R.drawable.diselected_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(15, 0, 15, 0);
                pageIndicator.addView(dots[i], params);
            }

            dots[0].setImageDrawable(activity.getResources().getDrawable(R.drawable.selected_dot));
        }

        if (viewPager != null) {
            syncDotsWithCurrentPage(viewPager.getCurrentItem());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { /* Auto-generated code */ }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(activity.getResources().getDrawable(R.drawable.diselected_dot));
        }
        dots[position].setImageDrawable(activity.getResources().getDrawable(R.drawable.selected_dot));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPageScrollStateChanged(int state) { /* Auto-generated code */ }

    /**
     * This method reset the ViewPager's selected page to the first one, it also
     * re-assigns the correct drawables to the LinearLayout who acts as indicator of the
     * page number. It is called when the activity enters in the onResume() state.
     *
     * @param positon Integer: the position to sync the drawable with
     */
    private void syncDotsWithCurrentPage(int positon) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(activity.getResources().getDrawable(R.drawable.diselected_dot));
        }
        dots[positon].setImageDrawable(activity.getResources().getDrawable(R.drawable.selected_dot));
    }
}
