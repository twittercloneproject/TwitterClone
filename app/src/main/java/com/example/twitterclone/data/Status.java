package com.example.twitterclone.data;

import java.util.List;

public class Status {
    private String name;
    private String message;
    private Alias alias;
    private String date;
    private int imageID;
    private int imageID1 = 0;


    public Status(String name, String message, Alias alias, String date, int imageID) {
        this.name = name;
        this.message = message;
        this.alias = alias;
        this.date = date;
        this.imageID = imageID;

    }

    public int getImageID1() {
        return imageID1;
    }

    public void setImageID1(int imageID1) {
        this.imageID1 = imageID1;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Alias getAlias() {
        return alias;
    }

    public String getDate() {
        return date;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}

