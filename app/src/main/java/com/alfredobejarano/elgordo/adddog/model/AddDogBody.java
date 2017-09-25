package com.alfredobejarano.elgordo.adddog.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class AddDogBody {
    @SerializedName("dog")
    private AddDogContent addDogContent;

    public AddDogBody() {
        // Empty constructor woof!
    }

    public AddDogBody(AddDogContent addDogContent) {
        this.addDogContent = addDogContent;
    }
}
