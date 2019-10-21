package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.List;

public class UserPresenter {
    private Model model = Model.getInstance();
    private User currentUser;
    private User viewedUser;


    public String getAlias() {
        currentUser = model.getCurrentUser();
        return currentUser.getAlias().getUsername();
    }

    public boolean isCurrentUser() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        if(currentUser.getAlias().getUsername().equals(viewedUser.getAlias().getUsername())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void follow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        viewedUser.addFollower(currentUser);
        currentUser.addFollowing(viewedUser);
    }

    public void unfollow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        viewedUser.removeFollower(currentUser);
        currentUser.removeFollowing(viewedUser);
    }

    public boolean search(String alias) {
        List<User> allUsers = model.getAllUsers();
        for(int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getAlias().getUsername().equals(alias)) {
                model.setViewedUser(allUsers.get(i));
                return true;
            }
        }
        return false;
    }

    public boolean isFollowing() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        List<User> following = currentUser.getFollowings();
        for(int i = 0; i < following.size(); i++) {
            if(following.get(i).getAlias().getUsername().equals(viewedUser.getAlias().getUsername())) {
                return true;
            }
        }
        return false;
    }

    public int getUserImage() {
        viewedUser = model.getViewedUser();
        return viewedUser.getImageID();
    }

}
