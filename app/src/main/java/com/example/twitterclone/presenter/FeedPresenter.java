package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Feed;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.User;

import java.util.List;

public class FeedPresenter {
    private Model model = Model.getInstance();

    public List<Status> getStatuses() {
        User viewedUser = model.getViewedUser();
        Feed currentFeed = viewedUser.getFeed();
        return currentFeed.getStatuses();
    }

    public List searchMessage(String message) {
        return model.searchAlias(message);
    }

    public void findUser(String alias) {
        User user = model.findUser(alias);
        model.setViewedUser(user);
    }

    public List findHash(String message) {
        return model.searchHashTags(message);
    }
}
