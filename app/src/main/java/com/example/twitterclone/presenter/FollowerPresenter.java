package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.List;

public class FollowerPresenter {
    private Model model = Model.getInstance();

    public List<User> getFollowers() {
        User viewedUser = model.getViewedUser();
        return viewedUser.getFollowers();
    }

}
