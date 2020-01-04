package com.example.twitterclone.presenter;

import android.os.AsyncTask;

import com.example.twitterclone.activity.FollowingFragment;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;

import java.util.List;

public class FollowingPresenter {
    private Model model = Model.getInstance();
    private FollowingFragment fragment;

    public FollowingPresenter(FollowingFragment fragment) {
        this.fragment = fragment;
    }

    public List<User> getFollowing() {
        User viewedUser = model.getViewedUser();
        return viewedUser.getFollowings();
    }

    public void getNextPageFollowing() {
        String[] reqs = new String[0];
        FollowingTask fTask = new FollowingTask();
        fTask.execute(reqs);
    }


    private class FollowingTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FollowRequests) {
            model.getNextFollowingPage();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean followingResult) {
            fragment.updateFollowing();
        }

    }

}
