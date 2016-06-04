package com.alfredobejarano.elgordo.network;

import com.alfredobejarano.elgordo.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface responsable for defining a call for the API to retrieve all the dog records,
 */
public interface GetAllDogsInterface {
    @GET("/dog")
    Call<List<Dog>> getDogs();
}
