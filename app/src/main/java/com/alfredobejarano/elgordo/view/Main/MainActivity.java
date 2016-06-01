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

public class MainActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainActivityPresenter().attachView(this);
    }

    @Override
    public void setup(Object data) {
        getSupportActionBar().hide();
        RecyclerView dogRecyclerView = (RecyclerView) findViewById(R.id.DogsRecyclerView);
        dogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dogRecyclerView.setAdapter(new DogRecyclerViewAdapter((List<Dog>) data));
    }
}
