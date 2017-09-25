package com.alfredobejarano.elgordo.adddog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 25/09/2017
 */

public class AddDogContent {
    @Expose
    private String image;
    @Expose
    private String breed;
    @Expose
    private String color;
    @Expose
    private String number;
    @Expose
    private boolean gender;
    @SerializedName("found_date")
    private String foundDate;
    @Expose
    private String description;
    @SerializedName("found_location")
    private String foundAddress;

    public AddDogContent() {
        // Empty method woof!
    }

    public AddDogContent(String description, String image, String number, String breed, String color, boolean gender, String foundAddress, String foundDate) {
        this.description = description;
        this.image = image;
        this.number = number;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.foundAddress = foundAddress;
        this.foundDate = foundDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getFoundAddress() {
        return foundAddress;
    }

    public void setFoundAddress(String foundAddress) {
        this.foundAddress = foundAddress;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }
}