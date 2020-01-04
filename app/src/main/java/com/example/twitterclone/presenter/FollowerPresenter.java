package com.example.twitterclone.presenter;

import android.os.AsyncTask;

import com.example.twitterclone.activity.FollowerFragment;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.List;

public class FollowerPresenter {
    private Model model = Model.getInstance();
    private FollowerFragment fragment;

    public FollowerPresenter(FollowerFragment fragment) {
        this.fragment = fragment;
    }

    public List<User> getFollowers() {
        User viewedUser = model.getViewedUser();
        return viewedUser.getFollowers();
    }

    public void getNextPageFollower() {
        String[] reqs = new String[0];
        FollowerTask fTask = new FollowerTask();
        fTask.execute(reqs);
    }


    private class FollowerTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FollowerRequests) {
            model.getNextFollowersPage();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean followingResult) {
            fragment.updateFollowers();
        }

    }

}
