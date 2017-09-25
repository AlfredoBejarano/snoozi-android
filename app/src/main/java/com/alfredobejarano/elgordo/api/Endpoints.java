package com.alfredobejarano.elgordo.api;


/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public class Endpoints {
    public static final String BASE_URL = "https://snoozi-api.herokuapp.com/";
    static final String DOGS = "dogs";
    static final String GET_DOG = "dogs/{" + URLFields.DOG_ID + "}";

    private Endpoints() {
        // Empty method woof!
    }

    static final class URLFields {
        static final String DOG_ID = "dog_id";
    }
}
