package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Alias;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

public class RegisterPresenter {

    private Model model = Model.getInstance();

    public boolean register(String firstName, String lastName, String username, String password) {
        model.setViewedUser(model.getCurrentUser());

        return true;
    }

}
