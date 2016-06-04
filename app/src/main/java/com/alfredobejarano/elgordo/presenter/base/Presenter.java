package com.alfredobejarano.elgordo.presenter.base;

import java.util.List;

/**
 * Interface responsable for all the presenters in the app.
 */
public interface Presenter {
    /**
     * This method is called from the ObjectMapper class to send the API
     * data to the presenter, to be parsed within the presenter and manipulate it
     * for accomplishing the view's needs.
     *
     * @param data - Object, the object to be parsed and used within the presenter.
     */
    void onResponse(Object data);

    /**
     * Callback method for triggering something when the data that came from
     * the API is invalid.
     *
     * @param error - Object, object to be parsed to throwable or manipulated for the callback's needs.
     */
    void onError(Object error);

    /**
     * This method connect this presenter to its corresponding view.
     * It receives a List of generic objects, the first one HAS TO BE a View,
     * the rest of the List can be any Object.
     *
     * @param data - The
     */
    void attachView(List<Object> params);
}