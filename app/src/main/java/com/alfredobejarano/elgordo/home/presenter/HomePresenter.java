package com.alfredobejarano.elgordo.home.presenter;

import com.alfredobejarano.elgordo.base.presenter.BasePresenter;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.home.model.DogListResponse;
import com.alfredobejarano.elgordo.home.model.HomeApiService;

import java.util.List;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public class HomePresenter extends BasePresenter<List<DogListResponse>> {
    /**
     * Initializes an instance of this class with a BaseActivity.
     *
     * @param activity - A BaseActivity instance.
     */
    public HomePresenter(BaseActivity activity) {
        super(activity);
        refreshDogs();
    }

    public void refreshDogs() {
        new HomeApiService(this).makeApiCall(null);
    }
}
