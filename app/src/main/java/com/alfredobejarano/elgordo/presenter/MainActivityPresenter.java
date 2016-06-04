package com.alfredobejarano.elgordo.presenter;

import com.alfredobejarano.elgordo.network.ObjectMapper;
import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

/**
 * Presenter for the main Activity
 */
public class MainActivityPresenter implements Presenter {
    private View view;

    /**
     * Method for obtaining the view private variable's value.
     *
     * @return View - The view private variable's value.
     */
    public View getView() {
        return view;
    }

    /**
     * Method for changing the view private variable's value.
     *
     * @param view - View, the View object's value to be used as a new value for the private variable.
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResponse(Object data) {
        view.setup(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onError(Object error) { /* Empty */ }

    /**
     * This method connect this presenter to its corresponding view.
     *
     * @param view - View, The view object that belongs to this presenter.
     */
    public void attachView(View view) {
        setView(view);
        ObjectMapper mapper = new ObjectMapper(this);
    }
}
