package com.alfredobejarano.elgordo.presenter;

import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.network.ObjectMapper;
import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

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
     * This method connect this presenter to its corresponding view.
     * This is also responsable for making the API call.
     *
     * @param view  - View, The view object that belongs to this presenter.
     * @param dogId - int, The ID of the dog to be retrieved.
     */
    public void attachView(View view, int dogId) {
        setView(view);
        ObjectMapper mapper = new ObjectMapper(this, dogId);
    }
}
