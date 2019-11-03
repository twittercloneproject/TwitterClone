package com.example.twitterclone.data;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private Alias alias;
    private Feed feed;
    private Story story;
    private List<User> followers;
    private List<User> followings;
    private String url;

    public User(String firstName, String lastName, Alias alias, String url) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.url = url;
        this.feed = new Feed();
        this.story = new Story();
        this.followings = new ArrayList<User>();
        this.followers = new ArrayList<User>();
    }

    public void addFollower(User u) {
        followers.add(u);
    }

    public void addFollowing(User u) {
        followings.add(u);
    }

    public void removeFollower(User u) {
        followers.remove(u);
    }

    public void removeFollowing(User u) {
        followings.remove(u);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Alias getAlias() {
        return alias;
    }

    public void setFollowings(List<User> followings) {
        this.followings = followings;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Feed getFeed() {
        return feed;
    }

    public Story getStory() {
        return story;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
