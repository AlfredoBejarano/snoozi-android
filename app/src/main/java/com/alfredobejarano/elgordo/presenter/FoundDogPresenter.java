package com.alfredobejarano.elgordo.presenter;

import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

import java.util.List;

/**
 * Created by Alfredo on 22/02/2017.
 */

public class FoundDogPresenter implements Presenter {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onResponse(Object data) {

    }

    @Override
    public void onError(Object error) {

    }

    @Override
    public void attachView(List<Object> params) {
        setView((View) params.get(0));
    }
}
