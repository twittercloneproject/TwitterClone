package com.example.twitterclone.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance = null;

    private User currentUser;
    private User viewedUser;
    private String password = "";
    private TwitterProxy proxy;


    private Model() {
        proxy = new TwitterProxy();
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            return instance;
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getViewedUser() {
        return viewedUser;
    }

    public List searchAlias(String message) {
        List<Integer> indexes = new ArrayList<Integer>();
        int index;
        int secondIndex;
        for (int i = 0; i < message.length(); ) {
            index = message.indexOf('@', i);
            if (index == -1) {
                return indexes;
            }
            else {
                indexes.add(index);
                secondIndex = message.indexOf(" ", index);
                if (secondIndex == -1) {
                    indexes.add(message.length()-1);
                    return indexes;
                } else {
                    indexes.add(secondIndex-1);
                    i = secondIndex;
                }
            }
        }
        return indexes;
    }

    public Status getStatus(String name, String date) {
      //  User u = this.getUser(name);
        List<Status> sts = this.viewedUser.getFeed().getStatuses();
        for (int i = 0; i < sts.size(); i++) {
            if (sts.get(i).getDate().equals(date)) {
                return sts.get(i);
            }
        }
        sts = viewedUser.getStory().getMyStatuses();
        for (int i = 0; i < sts.size(); i++) {
            if (sts.get(i).getDate().equals(date)) {
                return sts.get(i);
            }
        }
        return null;
    }

    public List searchHashTags(String message) {
        List<Integer> indexes = new ArrayList<Integer>();
        int index;
        int secondIndex;
        for (int i = 0; i < message.length(); ) {
            index = message.indexOf('#', i);
            if (index == -1) {
                return indexes;
            } else {
                indexes.add(index);
                secondIndex = message.indexOf(' ', index);
                if (secondIndex == -1) {
                    indexes.add(message.length());
                    return indexes;
                } else {
                    indexes.add(secondIndex - 1);
                    i = secondIndex;
                }
            }
        }
        return indexes;
    }

    public void setViewedUser(String alias) {
        User user = proxy.getUser(alias);
        Log.d("model", String.valueOf(user));
        this.viewedUser = user;
    }

    public void setViewedUserFeed(String alias) {
        Feed feed = proxy.getFeed(alias);
        this.viewedUser.setFeed(feed);
    }

    public void setViewedUserStory(String alias) {
        Story story = proxy.getStory(alias);
        this.viewedUser.setStory(story);
    }

    public void setViewedUserFollowers(String alias) {
        List<User> followers = proxy.getFollowers(alias);
        this.viewedUser.setFollowers(followers);
    }

    public void setViewedUserFollowing(String alias) {
        List<User> following = proxy.getFollowing(alias);
        this.viewedUser.setFollowings(following);
    }

    public boolean signIn(String alias, String password) {
        String message = proxy.signIn(alias, password);
        if(message.equals("success")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean register(String firstName, String lastName, String alias, String password, String profilePic) {
        String message = proxy.register(firstName, lastName, alias, password, profilePic);
        if(message.equals("success")) {
            return true;
        }
        else {
            return false;
        }
    }



}