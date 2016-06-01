package com.alfredobejarano.elgordo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.presenter.MainActivityPresenter;
import com.alfredobejarano.elgordo.view.base.View;

public class MainActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainActivityPresenter().attachView(this);
    }

    @Override
    public void setup(Object data) {
        TextView helloWorld = (TextView) findViewById(R.id.helloWorld);
        helloWorld.setText(String.valueOf(data));
    }
}
