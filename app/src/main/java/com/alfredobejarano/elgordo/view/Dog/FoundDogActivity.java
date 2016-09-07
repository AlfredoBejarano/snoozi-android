package com.alfredobejarano.elgordo.view.dog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.adapters.ViewPagerAdapter;
import com.alfredobejarano.elgordo.view.base.View;
import com.alfredobejarano.elgordo.view.listeners.FoundDogNextButtonClickListener;
import com.alfredobejarano.elgordo.view.listeners.ViewPagerChangeListener;

public class FoundDogActivity extends AppCompatActivity implements View {

    private Button nextButton;
    private Button readyButton;
    private FoundDogViewPager viewPager;
    private LinearLayout viewPagerIndicator;
    private ViewPagerChangeListener viewPagerChangeListener;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_dog);
        getSupportActionBar().hide();

        viewPagerIndicator = (LinearLayout) findViewById(R.id.found_dog_view_pager_indicator_layout);

        viewPager = (FoundDogViewPager) findViewById(R.id.found_dog_view_pager);
        viewPager.setAdapter(new ViewPagerAdapter());

        viewPagerChangeListener = new ViewPagerChangeListener(viewPagerIndicator, this, viewPager);
        viewPager.addOnPageChangeListener(viewPagerChangeListener);

        nextButton = (Button) findViewById(R.id.found_dog_flow_next_button);
        readyButton = (Button) findViewById(R.id.found_dog_flow_ready_button);

        nextButton.setOnClickListener(new FoundDogNextButtonClickListener(viewPager, nextButton, readyButton, this));
        readyButton.setOnClickListener(new FoundDogNextButtonClickListener(viewPager, nextButton, readyButton, this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(Object data) { /* This method is not necessary now */}

}
