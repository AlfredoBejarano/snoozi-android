package com.alfredobejarano.elgordo.viewdog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class ViewDogResponse {
    @Expose
    private int id;
    @Expose
    private String image;
    @Expose
    private String breed;
    @Expose
    private String color;
    @Expose
    private String number;
    @SerializedName("found_date")
    private String foundDate;
    @Expose
    private String description;
    @SerializedName("found_location")
    private String foundLocation;

    public ViewDogResponse(int id, String image, String breed, String color, String number, String foundDate, String description, String foundLocation) {
        this.id = id;
        this.image = image;
        this.breed = breed;
        this.color = color;
        this.number = number;
        this.foundDate = foundDate;
        this.description = description;
        this.foundLocation = foundLocation;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
