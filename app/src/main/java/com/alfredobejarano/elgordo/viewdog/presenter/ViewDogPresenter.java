package com.alfredobejarano.elgordo.viewdog.presenter;

import com.alfredobejarano.elgordo.base.presenter.BasePresenter;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.viewdog.model.ViewDogApiService;
import com.alfredobejarano.elgordo.viewdog.model.ViewDogResponse;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class ViewDogPresenter extends BasePresenter<ViewDogResponse> {
    /**
     * Initializes an instance of this class with a BaseActivity.
     *
     * @param activity - A BaseActivity instance.
     */
    public ViewDogPresenter(BaseActivity activity) {
        super(activity);
    }

    public void retrieveDog(Integer dogId) {
        try {
            new ViewDogApiService(this).makeApiCall(dogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
