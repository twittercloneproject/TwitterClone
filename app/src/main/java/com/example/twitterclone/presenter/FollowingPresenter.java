package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.List;

public class FollowingPresenter {
    private Model model = Model.getInstance();

    public List<User> getFollowing() {
        User viewedUser = model.getViewedUser();
        return viewedUser.getFollowings();
    }

}
