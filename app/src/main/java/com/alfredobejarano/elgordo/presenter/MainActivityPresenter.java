package com.alfredobejarano.elgordo.presenter;

import com.alfredobejarano.elgordo.network.ObjectMapper;
import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

import java.util.List;

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
     * {@inheritDoc}
     */
    @Override
    public void attachView(List<Object> params) {
        setView((View) params.get(0));
        ObjectMapper mapper = new ObjectMapper(this);
    }
}
