package com.alfredobejarano.elgordo.view.base;

/**
 * Created by jacorona on 6/1/16.
 * Activity
 * Interface responsable for all views behavior
 */
public interface View {
    /**
     * This method is called from the Presenter, it gets overloaded
     * with generic Object class, this generic object will be parsed
     * to the respective object depending on the view's needs.
     *
     * This method is also responsible for updating the layout's views
     * with the pertinent values from the Presenter.
     *
     * @param data Object - The generic object to be parsed within the method.
     */
    void setup(Object data);
}
