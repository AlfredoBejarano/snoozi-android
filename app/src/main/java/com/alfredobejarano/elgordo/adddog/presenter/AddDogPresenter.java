package com.alfredobejarano.elgordo.adddog.presenter;

import com.alfredobejarano.elgordo.adddog.model.AddDogApiService;
import com.alfredobejarano.elgordo.adddog.model.AddDogBody;
import com.alfredobejarano.elgordo.adddog.model.AddDogResponse;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;
import com.alfredobejarano.elgordo.base.view.BaseActivity;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class AddDogPresenter extends BasePresenter<AddDogResponse> {
    /**
     * Initializes an instance of this class with a BaseActivity.
     *
     * @param activity - A BaseActivity instance.
     */
    public AddDogPresenter(BaseActivity activity) {
        super(activity);
    }

    public void uploadDog(AddDogBody body) {
        new AddDogApiService(this).makeApiCall(body);
    }
}
