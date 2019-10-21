package com.example.twitterclone.presenter;

import com.example.twitterclone.data.Alias;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.ArrayList;
import java.util.List;

public class SignInPresenter {

    private Model model = Model.getInstance();


    public boolean login(String username, String password) {
        model.setViewedUser(model.getCurrentUser());
        return true;
    }
}
