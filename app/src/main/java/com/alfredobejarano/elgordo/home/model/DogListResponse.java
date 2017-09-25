package com.alfredobejarano.elgordo.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class DogListResponse {
    @Expose
    private int id;
    @Expose
    private String image;
    @Expose
    private String breed;
    @Expose
    private String color;
    @SerializedName("found_location")
    private String foundAddress;

    public DogListResponse(int id, String image, String breed, String color, String foundAddress) {
        this.id = id;
        this.image = image;
        this.breed = breed;
        this.color = color;
        this.foundAddress = foundAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFoundAddress() {
        return foundAddress;
    }

    public void setFoundAddress(String foundAddress) {
        this.foundAddress = foundAddress;
    }
}
