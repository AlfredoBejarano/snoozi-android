package com.alfredobejarano.elgordo.api;

import com.alfredobejarano.elgordo.adddog.model.AddDogBody;
import com.alfredobejarano.elgordo.adddog.model.AddDogResponse;
import com.alfredobejarano.elgordo.home.model.DogListResponse;
import com.alfredobejarano.elgordo.viewdog.model.ViewDogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Defines methods for retrieving / uploading dog data.
 *
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public interface Routes {
    /**
     * Retrieves all dogs.
     *
     * @return A Call object with a List of dogs.
     */
    @GET(Endpoints.DOGS)
    Call<List<DogListResponse>> getDogs();

    /**
     * Retrieves data of a single dog.
     *
     * @param dogId The dog id.
     * @return A Call object with a AddDogContent info.
     */
    @GET(Endpoints.GET_DOG)
    Call<ViewDogResponse> getDog(@Path(Endpoints.URLFields.DOG_ID) Integer dogId);

    /**
     * Saves a AddDogContent into the database.
     *
     * @param dog The dog information.
     * @return A call object with dog data attached to it.
     */
    @POST(Endpoints.DOGS)
    Call<AddDogResponse> uploadDog(@Body AddDogBody dog);
}
