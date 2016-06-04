package com.alfredobejarano.elgordo.presenter;

import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.network.ObjectMapper;
import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

import java.util.List;

/**
 * This class acts as the presenter for the View dog page.
 */
public class ViewDogPresenter implements Presenter {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResponse(Object data) {
        Dog dog = (Dog) data;

        String quotes = new String("\"");
        String newDescription = quotes + String.valueOf(dog.getDescription()) + quotes;
        dog.setDescription(newDescription);

        view.setup(dog);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onError(Object error) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachView(List<Object> params) {
        setView((View) params.get(0));
        ObjectMapper mapper = new ObjectMapper(this, (int) params.get(1));
    }
}
