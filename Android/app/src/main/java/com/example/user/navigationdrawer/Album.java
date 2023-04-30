package com.example.user.navigationdrawer;

public class Album {
    private String name;
    private String imageurl;

    public Album() {
    }

    public Album(String name, String url) {
        this.name = name;
        this.imageurl = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String url) {
        this.imageurl = url;
    }
}
