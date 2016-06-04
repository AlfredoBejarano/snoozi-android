package com.alfredobejarano.elgordo.network;

import com.alfredobejarano.elgordo.model.Dog;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This interface is responsable for defining the API call for retrieving or add a single dog record.
 */
public interface DogInterface {
    @GET("/dog/{dog_id}")
    Call<Dog> getDog(@Path("dog_id") int dog_id);

    @POST("/dog")
    Call<Dog> createDog(@Body Dog dog);
}
