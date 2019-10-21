package com.example.twitterclone.data;

import com.example.twitterclone.R;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance = null;

    private List<User> allUsers;
    private User currentUser;
    private User viewedUser;
    private String password = "";

    public List<User> getAllUsers() {
        return allUsers;
    }

    private Model() {

        Alias alias = new Alias("@test1");
        currentUser = new User("test1", "test1", alias, R.drawable.test1);
        Alias alias1 = new Alias("@test2");
        User user1 = new User("test2", "test2", alias1, R.drawable.test2);
        Alias alias2 = new Alias("@test3");
        User user2 = new User("test3", "test3", alias2, R.drawable.test3);
        Alias alias3 = new Alias("@test4");
        User user3 = new User("test4", "test4", alias3, R.drawable.test4);

        List<User> follow = new ArrayList<User>();
        follow.add(user1);
        follow.add(user2);
        follow.add(user3);
        currentUser.setFollowings(follow);
        currentUser.setFollowers(follow);
        follow = new ArrayList<User>();
        follow.add(currentUser);
        follow.add(user2);
        user1.setFollowers(follow);
        user1.setFollowings(follow);
        follow = new ArrayList<User>();
        follow.add(currentUser);
        follow.add(user1);
        user2.setFollowings(follow);
        user2.setFollowers(follow);
        follow = new ArrayList<User>();
        follow.add(currentUser);
        user3.setFollowers(follow);
        user3.setFollowings(follow);


        Status status = new Status(currentUser.getFirstName() + ' ' + currentUser.getLastName(),
                "first #forever tweet @test2", currentUser.getAlias(), "Oct 4", R.drawable.test1);
        Status status1 = new Status(currentUser.getFirstName() +  ' ' + currentUser.getLastName(),
                "second tweet @test3", currentUser.getAlias(), "Oct 4", R.drawable.test1);
        Status status2 = new Status(currentUser.getFirstName() + ' ' + currentUser.getLastName(),
                "third @test2 tweet @test4", currentUser.getAlias(), "Oct 4", R.drawable.test1);
        Status status3 = new Status(user1.getFirstName() + ' ' + user1.getLastName(),
                "fourth tweet @test1 ", user1.getAlias(), "Oct 4", R.drawable.test2);
        Status status4 = new Status(user1.getFirstName() + ' ' + user1.getLastName(),
                "fifth tweet @test3 @test1", user1.getAlias(), "Oct 4", R.drawable.test2);
        Status status5 = new Status(user3.getFirstName() + ' ' + user3.getLastName(),
                "sixth tweet @test1", user3.getAlias(), "Oct 4", R.drawable.test4);
        status.setImageID1(R.drawable.test3);
        List<Status> statuses = new ArrayList<Status>();
        statuses.add(status);
        statuses.add(status1);
        statuses.add(status2);
        Story story = new Story();
        story.setMyStatuses(statuses);
        currentUser.setStory(story);
        statuses = new ArrayList<Status>();
        statuses.add(status3);
        statuses.add(status4);
        story = new Story();
        story.setMyStatuses(statuses);
        user1.setStory(story);
        statuses = new ArrayList<Status>();
        statuses.add(status5);
        story = new Story();
        story.setMyStatuses(statuses);
        user3.setStory(story);

        statuses = new ArrayList<Status>();
        statuses.add(status3);
        statuses.add(status4);
        statuses.add(status5);
        Feed feed = new Feed();
        feed.setStatuses(statuses);
        currentUser.setFeed(feed);
        statuses = new ArrayList<Status>();
        statuses.add(status);
        statuses.add(status1);
        statuses.add(status2);
        feed = new Feed();
        feed.setStatuses(statuses);
        user1.setFeed(feed);
        statuses = new ArrayList<Status>();
        statuses.add(status);
        statuses.add(status1);
        statuses.add(status2);
        statuses.add(status3);
        statuses.add(status4);
        feed = new Feed();
        feed.setStatuses(statuses);
        user2.setFeed(feed);
        statuses = new ArrayList<Status>();
        statuses.add(status);
        statuses.add(status1);
        statuses.add(status2);
        feed = new Feed();
        feed.setStatuses(statuses);
        user3.setFeed(feed);
        setCurrentUser(currentUser);
        allUsers = new ArrayList<User>();
        allUsers.add(currentUser);
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
    }

    public static Model getInstance() {
        if(instance == null) {
            instance = new Model();
            return instance;
        }
        return instance;
    }

    public User findUser(String testAlias) {
        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getAlias().getUsername().equals(testAlias)) {
                return allUsers.get(i);
            }
        }
        return null;
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

    public void setViewedUser(User viewedUser) {
        this.viewedUser = viewedUser;
    }

    public List searchAlias(String message) {
        List indexs = new ArrayList();
        int index;
        int secondIndex;
        for(int i = 0; i < message.length();) {
            index = message.indexOf('@', i);
            if(index == -1) {
                return indexs;
            }
            else {
                indexs.add(index);
                secondIndex = message.indexOf(' ', index);
                if(secondIndex == -1) {
                    indexs.add(message.length()-1);
                    return indexs;
                }
                else {
                    indexs.add(secondIndex-1);
                    i = secondIndex;
                }
            }
        }
        return indexs;
    }

    public List searchHashTags(String message) {
        List indexs = new ArrayList();
        int index;
        int secondIndex;
        for(int i = 0; i < message.length();) {
            index = message.indexOf('#', i);
            if(index == -1) {
                return indexs;
            }
            else {
                indexs.add(index);
                secondIndex = message.indexOf(' ', index);
                if(secondIndex == -1) {
                    indexs.add(message.length()-1);
                    return indexs;
                }
                else {
                    indexs.add(secondIndex-1);
                    i = secondIndex;
                }
            }
        }
        return indexs;
    }
}
