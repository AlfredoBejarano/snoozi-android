package com.alfredobejarano.elgordo.model;

import android.graphics.Bitmap;

import retrofit2.http.POST;

/**
 * Created by jacorona on 6/1/16.
 */
public class Dog {
    private int id;
    private String breed;
    private String color;
    private boolean gender;
    private String found_location;
    private String found_date;
    private String description;
    private Long number;
    private String created_at;
    private String thumb_url;
    private String image_url;
    private String image;
    private Bitmap downloadedImage;

    /**
     * This constructor is for the "get all dogs" (MainActivity) use case.
     * @param id - The dog's id from the database.
     * @param breed - The breed of the dog.
     * @param color - The fur color of the dog.
     * @param gender - The gender of the dog (true for male, false for female).
     * @param found_location - The coordenates of the dog (xx.xxxx , xx.xxxx format).
     * @param found_date - The date when the dog was found.
     * @param description - The description of the dog.
     * @param created_at - The date when the dog was added to the database.
     * @param thumb_url - The URL of the thumbnail image.
     */
    public Dog(int id, String breed, String color, boolean gender, String found_location, String found_date, String description, String created_at, String thumb_url) {
        this.id = id;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.found_location = found_location;
        this.found_date = found_date;
        this.description = description;
        this.created_at = created_at;
        this.thumb_url = thumb_url;
    }

    /**
     * This constructor is for the "get a single dog" use case.
     * * @param id - The dog's id from the database.
     * @param breed - The breed of the dog.
     * @param color - The fur color of the dog.
     * @param gender - The gender of the dog (true for male, false for female).
     * @param found_location - The coordenates of the dog (xx.xxxx , xx.xxxx format).
     * @param found_date - The date when the dog was found.
     * @param description - The description of the dog.
     * @param created_at - The date when the dog was added to the database.
     * @param number - The number of the user who found the dog.
     * @param image_url - The URL of the dog's photo.
     */
    public Dog(int id, String breed, String color, boolean gender, String found_location, String found_date, String description, Long number, String created_at, String image_url) {
        this.id = id;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.found_location = found_location;
        this.found_date = found_date;
        this.description = description;
        this.number = number;
        this.created_at = created_at;
        this.image_url = image_url;
    }

    /**
     * This constructor is for the "add a dog to the database" (FoundDogActivity) use case.
     * @param breed - The breed of the dog.
     * @param color - The fur color of the dog.
     * @param gender - The gender of the dog (true for male, false for female).
     * @param found_location - The coordenates of the dog (xx.xxxx , xx.xxxx format).
     * @param found_date - The date when the dog was found.
     * @param description - The description of the dog.
     * @param number - The number of the user who found the dog.
     * @param image - The photo taken by the user of the dog (in Base64 String format).
     */
    public Dog(String breed, String color, boolean gender, String found_location, String found_date, String description, Long number, String image) {
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.found_location = found_location;
        this.found_date = found_date;
        this.description = description;
        this.number = number;
        this.image = image;
    }

    /**
     * This constructor is for the "display a single dog" (ViewDogActivity) use case.
     * * @param id - The dog's id from the database.
     * @param breed - The breed of the dog.
     * @param color - The fur color of the dog.
     * @param gender - The gender of the dog (true for male, false for female).
     * @param found_location - The coordenates of the dog (xx.xxxx , xx.xxxx format).
     * @param found_date - The date when the dog was found.
     * @param description - The description of the dog.
     * @param number - The number of the user who found the dog.
     * @param downloadedImage - The URL of the dog's photo.
     */
    public Dog(String breed, String color, boolean gender, String found_location, String found_date, String description, Long number, Bitmap downloadedImage) {
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.found_location = found_location;
        this.found_date = found_date;
        this.description = description;
        this.number = number;
        this.downloadedImage = downloadedImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFound_location() {
        return found_location;
    }

    public void setFound_location(String found_location) {
        this.found_location = found_location;
    }

    public String getFound_date() {
        return found_date;
    }

    public void setFound_date(String found_date) {
        this.found_date = found_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getDownloadedImage() {
        return downloadedImage;
    }

    public void setDownloadedImage(Bitmap downloadedImage) {
        this.downloadedImage = downloadedImage;
    }


}
