package com.alfredobejarano.elgordo.network;

/**
 * This class contains a list of constants used for flags and that sort of stuff within the app.
 * This class purpose is prevent the use of ENUMS.
 */
public class NetworkUtils {
    // TODO - Change the API URL for the production one (when the API enters production m8).
    // public static final String API_BASE_URL = "http://10.16.10.15:3000";
    public static final String API_BASE_URL = "http://192.168.0.54:3000";

    /* Static flags */
    public static final int GET_ALL_DOGS = 0;
    public static final int GET_A_DOG = 1;
    public static final int CREATE_A_DOG = 2;
}
