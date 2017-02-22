package com.alfredobejarano.elgordo.view.dog;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.presenter.FoundDogPresenter;
import com.alfredobejarano.elgordo.view.adapters.FoundDogViewPagerAdapter;
import com.alfredobejarano.elgordo.view.base.View;
import com.alfredobejarano.elgordo.view.listeners.FoundDogNextButtonClickListener;
import com.alfredobejarano.elgordo.view.listeners.ViewPagerChangeListener;
import com.alfredobejarano.elgordo.view.pages.MapPage;
import com.alfredobejarano.elgordo.view.pages.WidgetPage;
import com.alfredobejarano.elgordo.view.pages.RadiusOptionPage;
import com.alfredobejarano.elgordo.view.pages.TextInputPage;
import com.alfredobejarano.elgordo.view.pages.WelcomePage;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class FoundDogActivity extends AppCompatActivity
        implements View, WelcomePage.OnFragmentInteractionListener, TextInputPage.OnFragmentInteractionListener,
        RadiusOptionPage.OnFragmentInteractionListener, WidgetPage.OnFragmentInteractionListener, MapPage.OnFragmentInteractionListener {

    private ArrayList params;
    private Button nextButton;
    private FoundDogViewPager viewPager;
    private LinearLayout viewPagerIndicator;
    private GoogleApiClient googleApiClient;
    private ViewPagerChangeListener viewPagerChangeListener;
    private FoundDogViewPagerAdapter foundDogViewPagerAdapter;

    public ArrayList getParams() { return params; }
    public void setParams(ArrayList params) { this.params = params; }
    public FoundDogViewPager getViewPager() { return viewPager;}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_dog);
        getSupportActionBar().hide();

        params = new ArrayList();

        viewPagerIndicator = (LinearLayout) findViewById(R.id.found_dog_view_pager_indicator_layout);

        viewPager = (FoundDogViewPager) findViewById(R.id.found_dog_view_pager);
        foundDogViewPagerAdapter = new FoundDogViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(foundDogViewPagerAdapter);

        viewPagerChangeListener = new ViewPagerChangeListener(viewPagerIndicator, this, viewPager);
        viewPager.addOnPageChangeListener(viewPagerChangeListener);

        nextButton = (Button) findViewById(R.id.found_dog_flow_next_button);
        nextButton.setOnClickListener(new FoundDogNextButtonClickListener(viewPager, nextButton, this));



    }

    /**
     * This method changes the behaviour when the back button is pressed in this activity.
     * If the current page number is bigger than 0, it send the user to the previous page.
     * If not, it sends the user back to the previous activity.
     */
    @Override
    public void onBackPressed() {
        if(viewPager != null) {
            int currentPage = viewPager.getCurrentItem();
            if( currentPage > 0) {
                viewPager.setCurrentItem(currentPage - 1);
                nextButton.setText(getResources().getText(R.string.next));
            } else {
                super.onBackPressed();
            }
        }
    }

    public void uploadDog() {
        ArrayList<Object> presenterParams = new ArrayList<>();
        presenterParams.add(this);
        presenterParams.add(this.params);

        new FoundDogPresenter().attachView(presenterParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(Object data) { /* This method is not necessary now */ }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFragmentInteraction(Uri uri) { /*  auto-generated code */ }

}
