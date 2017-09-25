package com.alfredobejarano.elgordo.viewdog.model;

import com.alfredobejarano.elgordo.base.model.BaseApiService;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class ViewDogApiService extends BaseApiService<ViewDogResponse, Integer> {
    public ViewDogApiService(BasePresenter presenter) {
        super(presenter);
    }

    @Override
    public void makeApiCall(Integer body) {
        mCall = mRoutes.getDog(body);
        mCall.enqueue(this);
    }
}
