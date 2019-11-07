package com.example.twitterclone.data;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private String name;
    private String message;
    private Alias alias;
    private String date;
    private String url;
    private List<String> hashtags;
    private int id;
    private String profilePic;


    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Status(String name, String message, Alias alias, String date, String url, int id, String profilePic) {
        this.name = name;
        this.message = message;
        this.alias = alias;
        this.date = date;
        this.url = url;
        this.hashtags = new ArrayList<String>();
        this.id = id;
        this.profilePic = profilePic;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void addHashtags(String hashtag) {
        this.hashtags.add(hashtag);
    }

    public int getId() {
        return id;
    }

    public String getProfilePic() {
        return profilePic;
    }
}

