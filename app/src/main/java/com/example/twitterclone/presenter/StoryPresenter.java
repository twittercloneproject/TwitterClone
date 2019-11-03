package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.Story;
import com.example.twitterclone.data.User;

import java.util.List;

public class StoryPresenter {
    private Model model = Model.getInstance();


    public List<Status> getStatuses() {
        User viewedUser = model.getViewedUser();
        Story currentStory = viewedUser.getStory();
        return currentStory.getMyStatuses();
    }

    public Status getStatus(String alias, String date) {
        return model.getStatus(alias, date);
    }

    public User getUser(String alias) {
        return model.getUser(alias);
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
