package com.alfredobejarano.elgordo.base.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;
import com.alfredobejarano.elgordo.common.factory.Error;

import butterknife.ButterKnife;

/**
 * Defines methods for a View for this project.
 *
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public abstract class BaseActivity<T, E extends BasePresenter<T>> extends AppCompatActivity {
    public static final int ERROR_GENERIC = R.string.error_service_unavailable;
    protected int mMenu = 0;
    protected E mPresenter;

    /**
     * Inflates a menu, <b>a menu resource id must be defined in onCreate method</b>
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMenu != 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(mMenu, menu);
        } else {
            super.onCreateOptionsMenu(menu);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bindViews();
    }

    /**
     * Defines the layout content for this activity.
     *
     * @return The id of the layout.
     */
    protected abstract int getLayout();

    /**
     * Calls ButterKnife to bind the views of this Activity.
     */
    protected void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * Assigns a presenter instance to this view and vice versa.
     *
     * @param presenter - The presenter instance.
     */
    protected void attachPresenter(BasePresenter<T> presenter) {
        mPresenter = (E) presenter;
    }

    /**
     * This method displays all the data into widgets/views.
     *
     * @param data The response from the presenter.
     */
    public abstract void setup(T data);

    /**
     * Changes view visibility in a null pointer exception free method.
     */
    protected void changeViewVisibility(View v, int state) {
        if (v != null) {
            v.setVisibility(state);
        }
    }

    /**
     * This method handles error displaying widgets/views.
     *
     * @param stringResId Resource ID for an string displaying the error.
     */
    public void displayError(int stringResId) {
        Snackbar.make(findViewById(android.R.id.content), stringResId, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Shows or hides the view responsible for notifying loading.
     *
     * @param visibility The display value for the view.
     */
    public void changeLoadingVisibility(int visibility) {
        changeViewVisibility(findViewById(R.id.loading_view), visibility);
    }

    /**
     * This method sets the Toolbar title and navigation icon.
     */
    protected void setupToolBar(String title, boolean showBackArrow) {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showBackArrow);
            getSupportActionBar().setTitle(title);
        } catch (Exception e) {
            displayError(Error.Factory(ERROR_GENERIC));
        }
    }
}
