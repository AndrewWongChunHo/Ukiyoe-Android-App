package com.example.ea_android_wongchunho.Models;

//The Model for Profile fragment
public class item {
    int image;
    String text;


    //Constructor
    public item(int image, String text) {
        this.image = image;
        this.text = text;
    }

    //Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
