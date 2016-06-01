package com.alfredobejarano.elgordo.view.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.presenter.MainActivityPresenter;
import com.alfredobejarano.elgordo.view.base.View;

import java.util.List;

/**
 * MainActivity
 * This class functions as the first activity of the application and as the view of the Main presenter.
 */
public class MainActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainActivityPresenter().attachView(this);
    }

    @Override
    public void setup(Object data) {
        getSupportActionBar().hide(); // Hides ActionBar

        /* RecyclerView configurations */
        RecyclerView dogRecyclerView = (RecyclerView) findViewById(R.id.DogsRecyclerView);
        dogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dogRecyclerView.setAdapter(new DogRecyclerViewAdapter((List<Dog>) data));
    }
}
