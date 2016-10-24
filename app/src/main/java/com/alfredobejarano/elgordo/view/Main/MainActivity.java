package com.alfredobejarano.elgordo.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.presenter.MainActivityPresenter;
import com.alfredobejarano.elgordo.view.adapters.DogRecyclerViewAdapter;
import com.alfredobejarano.elgordo.view.dog.FoundDogActivity;
import com.alfredobejarano.elgordo.view.dog.ViewDogActivity;
import com.alfredobejarano.elgordo.view.base.View;

import java.util.ArrayList;
import java.util.List;

/**
 * This class functions as the first activity of the application and as the view of the Main presenter.
 */
public class MainActivity extends AppCompatActivity implements View {

    private RecyclerView dogRecyclerView;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Object> params = new ArrayList<>();
        params.add(this);

        new MainActivityPresenter().attachView(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup(Object data) {
        dogRecyclerView = (RecyclerView) findViewById(R.id.DogsRecyclerView);
        dogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dogRecyclerView.setAdapter(new DogRecyclerViewAdapter(((List<Dog>) data),this));
    }

    /**
     * This method calls a View Page Dog Activity,
     * it sends via Intent the ID of the selected dog from
     * the RecyclerView (dogRecyclerView).
     *
     * @param dogId - int, the chosen dog's id.
     */
    public void openDogActivity(int dogId) {
        Intent intent = new Intent(this, ViewDogActivity.class);
        intent.putExtra("DOG_ID",dogId);
        startActivity(intent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_view_menu, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add_dog:
                startActivity(new Intent(this, FoundDogActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
