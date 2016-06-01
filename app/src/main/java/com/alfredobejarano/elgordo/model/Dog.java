package com.alfredobejarano.elgordo.model;

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
    private String created_at;
    private String thumb_url;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public boolean isGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public String getFound_location() { return found_location; }
    public void setFound_location(String found_location) { this.found_location = found_location; }

    public String getFound_date() { return found_date; }
    public void setFound_date(String found_date) { this.found_date = found_date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public String getThumb_url() { return thumb_url; }
    public void setThumb_url(String thumb_url) { this.thumb_url = thumb_url; }
}
