package com.alfredobejarano.elgordo.base.presenter;

import android.support.annotation.StringRes;

import com.alfredobejarano.elgordo.base.view.BaseActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * This class manages information from / for thhe views.
 *
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public class BasePresenter<T> {
    private BaseActivity<T, BasePresenter<T>> mActivity;

    /**
     * Initializes an instance of this class with a BaseActivity.
     *
     * @param activity - A BaseActivity instance.
     */
    public BasePresenter(BaseActivity activity) {
        mActivity = activity;
        activity.changeLoadingVisibility(VISIBLE);
    }

    /**
     * Sends data to this instance's BaseActivity.
     *
     * @param response - A dog response from the service.
     */
    public void onResponse(T response) {
        mActivity.setup(response);
    }

    /**
     * Display an error if the server responded with one.
     *
     * @param errorCode the Error code string Id.
     */
    public void onError(@StringRes int errorCode) {
        mActivity.changeLoadingVisibility(GONE);
        mActivity.displayError(errorCode);
    }
}
