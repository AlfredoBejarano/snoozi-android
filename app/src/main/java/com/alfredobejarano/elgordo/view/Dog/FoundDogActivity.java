package com.alfredobejarano.elgordo.view.dog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.view.base.View;

public class FoundDogActivity extends AppCompatActivity implements View {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_dog);
        getSupportActionBar().hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(Object data) { /* This method is not necessary now */}
}
