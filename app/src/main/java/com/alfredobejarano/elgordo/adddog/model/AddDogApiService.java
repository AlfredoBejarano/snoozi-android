package com.alfredobejarano.elgordo.adddog.model;

import com.alfredobejarano.elgordo.base.model.BaseApiService;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class AddDogApiService extends BaseApiService<AddDogResponse, AddDogBody> {
    public AddDogApiService(BasePresenter presenter) {
        super(presenter);
    }

    @Override
    public void makeApiCall(AddDogBody body) {
        mCall = mRoutes.uploadDog(body);
        mCall.enqueue(this);
    }
}
