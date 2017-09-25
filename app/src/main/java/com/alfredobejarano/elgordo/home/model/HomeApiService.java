package com.alfredobejarano.elgordo.home.model;

import com.alfredobejarano.elgordo.base.model.BaseApiService;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;

import java.util.List;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class HomeApiService extends BaseApiService<List<DogListResponse>, Void> {
    public HomeApiService(BasePresenter presenter) {
        super(presenter);
        makeApiCall(null);
    }

    @Override
    public void makeApiCall(Void body) {
        mCall = mRoutes.getDogs();
        mCall.enqueue(this);
    }
}
