package com.example.twitterclone.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance = null;

    private User currentUser;
    private User viewedUser;
    private List<Status> hashtagStatuses;
    private String password = "";
    private TwitterProxy proxy;
    private String currentAuthToken = "";


    private Model() {
        proxy = new TwitterProxy();
        hashtagStatuses = new ArrayList<Status>();
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

    public List<Status> getHashtagStatuses() {
        return this.hashtagStatuses;
    }

    public List<Integer> searchAlias(String message) {
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
        List<Status> sts = this.viewedUser.getFeed().getStatuses();
        for (int i = 0; i < sts.size(); i++) {
            if (sts.get(i).getDate().equals(date) && sts.get(i).getAlias().getUsername().equals(name)) {
                return sts.get(i);
            }
        }
        sts = viewedUser.getStory().getMyStatuses();
        for (int i = 0; i < sts.size(); i++) {
            if (sts.get(i).getDate().equals(date) && sts.get(i).getAlias().getUsername().equals(name)) {
                return sts.get(i);
            }
        }
        sts = hashtagStatuses;
        for (int i = 0; i < sts.size(); i++) {
            if (sts.get(i).getDate().equals(date) && sts.get(i).getAlias().getUsername().equals(name)) {
                return sts.get(i);
            }
        }
        Log.d("activity", "null");
        return null;
    }

    public List<Integer> searchHashTags(String message) {
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
                    indexes.add(message.length()-1);
                    return indexes;
                } else {
                    indexes.add(secondIndex - 1);
                    i = secondIndex;
                }
            }
        }
        return indexes;
    }

    public boolean setViewedUser(String alias) {
        User user = proxy.getUser(alias);
        if(user == null) {
            return false;
        }
        this.viewedUser = user;
        return true;
    }

    public void setViewedUserFeed(String alias) {
        Feed feed = proxy.getFeed(alias, "");
        this.viewedUser.setFeed(feed);
    }

    public void setViewedUserStory(String alias) {
        Story story = proxy.getStory(alias, "");
        this.viewedUser.setStory(story);
    }

    public void setViewedUserFollowers(String alias) {
        List<User> followers = proxy.getFollowers(alias, "");
        this.viewedUser.setFollowers(followers);
    }

    public void setViewedUserFollowing(String alias) {
        List<User> following = proxy.getFollowing(alias, "");
        this.viewedUser.setFollowings(following);
    }

    public boolean signIn(String alias, String password) {
        String message = proxy.signIn(alias, password);
        if(message.equals("failure")) {
            return false;
        }
        else {
            this.currentAuthToken = message;
            return true;
        }
    }

    public boolean register(String firstName, String lastName, String alias, String password, String profilePic) {
        String message = proxy.register(firstName, lastName, alias, password, profilePic);
        if(message.equals("failure")) {
            return false;
        }
        else {
            this.currentAuthToken = message;
            return true;
        }
    }

    public boolean setHashtagStatuses(String hashtag) {
        this.hashtagStatuses = this.proxy.getHashtag(hashtag, "");
        return true;
    }

    public void getNextFeedPage() {
        List<Status> newList = this.viewedUser.getFeed().getStatuses();
        if(newList.size() == 0) {
            return;
        }
        Feed tmpList = proxy.getFeed(this.viewedUser.getAlias().getUsername(), newList.get(newList.size()-1).getDate());
        newList.addAll(tmpList.getStatuses());
        this.viewedUser.getFeed().setStatuses(newList);
    }

    public void getNextStoryPage() {
        List<Status> newList = this.viewedUser.getStory().getMyStatuses();
        if(newList.size() == 0) {
            return;
        }
        Story tmpList = proxy.getStory(this.viewedUser.getAlias().getUsername(), newList.get(newList.size()-1).getDate());
        newList.addAll(tmpList.getMyStatuses());
        Log.d("activity",newList.toString());
        this.viewedUser.getStory().setMyStatuses(newList);
    }

    public void getNextHashtagPage(String hashtag) {
        if(this.hashtagStatuses.size() == 0) {
            return;
        }
        List<Status> tmpList = proxy.getHashtag(hashtag, this.hashtagStatuses.get(this.hashtagStatuses.size()-1).getDate());
        this.hashtagStatuses.addAll(tmpList);
    }

    public void getNextFollowersPage() {
        List<User> newList = this.viewedUser.getFollowers();
        if(newList.size() == 0) {
            return;
        }
        List<User> tmpList = proxy.getFollowers(this.viewedUser.getAlias().getUsername(), newList.get(newList.size()-1).getAlias().getUsername());
        newList.addAll(tmpList);
        this.viewedUser.setFollowers(newList);
    }

    public void getNextFollowingPage() {
        List<User> newList = this.viewedUser.getFollowings();
        if(newList.size() == 0) {
            return;
        }
        List<User> tmpList = proxy.getFollowing(this.viewedUser.getAlias().getUsername(), newList.get(newList.size()-1).getAlias().getUsername());
        newList.addAll(tmpList);
        this.viewedUser.setFollowings(newList);
    }

    public void follow(String followerUsername, String followeeUsername) {
        this.proxy.follow(followerUsername, followeeUsername);
        this.currentUser.addFollowing(this.viewedUser);
        this.viewedUser.addFollower(this.currentUser);
    }

    public void unfollow(String followerUsername, String followeeUsername) {
        this.proxy.unFollow(followerUsername, followeeUsername);
        this.currentUser.removeFollowing(this.viewedUser);
        this.viewedUser.removeFollower(this.currentUser);
    }

    public boolean isFollowing(String currentUsername, String otherUsername) {
        return this.proxy.isFollowing(currentUsername, otherUsername);
    }

    public void sendStatus(String username, String name, String message, String date,
                           List<String> hashtags, String profilePic, String attachment) {
        this.proxy.sendStatus(name, username, message, date, attachment, profilePic, hashtags);
    }
}