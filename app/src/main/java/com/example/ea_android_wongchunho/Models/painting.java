package com.example.ea_android_wongchunho.Models;

public class painting {

    // The model for the home fragment showcasing paintings
    String image;
    String name;
    String year;
    String artist;
    String description;

    //Empty constructor is needed
    public painting() {
    }

    //Constructor
    public painting(String image, String name, String year, String artist, String description) {
        this.image = image;
        this.name = name;
        this.year = year;
        this.artist = artist;
        this.description = description;
    }

    //Getters and Setters
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
