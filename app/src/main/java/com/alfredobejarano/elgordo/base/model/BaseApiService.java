package com.alfredobejarano.elgordo.base.model;

import com.alfredobejarano.elgordo.BuildConfig;
import com.alfredobejarano.elgordo.api.Endpoints;
import com.alfredobejarano.elgordo.api.Routes;
import com.alfredobejarano.elgordo.base.presenter.BasePresenter;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.common.factory.Error;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alfredobejarano.elgordo.base.view.BaseActivity.ERROR_GENERIC;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * This class handles the IO of the info from the databse.
 *
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public abstract class BaseApiService<T, E> implements Callback<T> {

    protected Call<T> mCall;
    protected Routes mRoutes;
    private BasePresenter<T> mPresenter;

    public BaseApiService(BasePresenter presenter) {
        mPresenter = presenter;
        try {
            initClient();
        } catch (Exception e) {
            mPresenter.onError(ERROR_GENERIC);
        }
    }

    /**
     * Makes the pertinent call to perform something with the server.
     * mCall = mRoutes.<method>;
     * mCall.enqueue(this);
     */
    public abstract void makeApiCall(E body);

    /**
     * This method initializes the service
     */
    private void initClient() {
        Retrofit restClient = buildRetrofitClient();
        mRoutes = restClient.create(Routes.class);
    }

    /**
     * This method builds the Retrofit client to be used by
     * the initClient method.
     *
     * @return The Retrofit client.
     */
    private Retrofit buildRetrofitClient() {
        Retrofit.Builder restClient = new Retrofit.Builder().baseUrl(Endpoints.BASE_URL).addConverterFactory(GsonConverterFactory.create());

        if (BuildConfig.DEBUG) {
            restClient.client(buildHttpInterceptor());
        }

        return restClient.build();
    }

    /**
     * This method creates an interceptor for logging the JSON
     * body of the responses to the Android Monitor Logcat.
     *
     * @return The OkHttp Interceptor
     */
    private OkHttpClient buildHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            mPresenter.onResponse(response.body());
        } else {
            mPresenter.onError(Error.Factory(response.code()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mPresenter.onError(t instanceof HttpException ? Error.Factory(((HttpException) t).code()) : BaseActivity.ERROR_GENERIC);
    }
}
