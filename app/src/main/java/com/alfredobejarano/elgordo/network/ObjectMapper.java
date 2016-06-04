package com.alfredobejarano.elgordo.network;


import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.presenter.base.Presenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is responsible for calling the Snoozi API
 * and sending this responses to a presenter.
 */
public class ObjectMapper {
    /**
     * This constructor makes a call for getting all the dog records.
     *
     * @param presenter the presenter for the desired view
     */
    public ObjectMapper(final Presenter presenter) {
        Retrofit httpRestClient = new Retrofit.Builder()
                .baseUrl(NetworkUtils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetAllDogsInterface getAllDogsInterface = httpRestClient.create(GetAllDogsInterface.class);
        Call<List<Dog>> apiCall = getAllDogsInterface.getDogs();

        apiCall.enqueue(new Callback<List<Dog>>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                presenter.onResponse(response.body());
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                presenter.onError(t);
            }
        });

    }


    /**
     * This constructor makes a call for getting a dog;
     *
     * @param presenter the presenter for the desired view
     * @param dog_id    the dog id of the dog to be retrieved
     */
    public ObjectMapper(final Presenter presenter, int dog_id) {
        Retrofit httpRestClient = new Retrofit.Builder()
                .baseUrl(NetworkUtils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogInterface dogInterface = httpRestClient.create(DogInterface.class);
        Call<Dog> apiCall = dogInterface.getDog(dog_id);

        apiCall.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                presenter.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                presenter.onError(t);
            }
        });
    }


    /**
     * This constructor makes a call for adding a  dog to the database;
     *
     * @param presenter the presenter for the desired view
     * @param body      the Dog object to be parsed and send to the API
     */
    public ObjectMapper(final Presenter presenter, final Dog body) {
        Retrofit httpRestClient = new Retrofit.Builder()
                .baseUrl(NetworkUtils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogInterface dogInterface = httpRestClient.create(DogInterface.class);
        Call<Dog> apiCall = dogInterface.createDog(body);

        apiCall.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                presenter.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                presenter.onError(t);
            }
        });
    }
}
