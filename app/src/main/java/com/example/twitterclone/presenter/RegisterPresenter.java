package com.example.twitterclone.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitterclone.activity.RegisterActivity;
import com.example.twitterclone.data.Model;

public class RegisterPresenter {

    private Model model = Model.getInstance();
    private String alias;
    private RegisterActivity activity;


    public void register(String firstName, String lastName, String username, String password, String profilePic, RegisterActivity activity) {
        this.alias = username;
        this.activity = activity;
        String[] reqs = new String[5];;
        reqs[0] = firstName;
        reqs[1] = lastName;
        reqs[2] = username;
        reqs[3] = password;
        reqs[4] = profilePic;
        RegisterTask sTask = new RegisterTask();
        sTask.execute(reqs);
    }



    private class RegisterTask extends AsyncTask<String, Void, Boolean> {


        @Override
        protected Boolean doInBackground(String... registerRequests) {
            if(model.register(registerRequests[0], registerRequests[1], registerRequests[2], registerRequests[3], registerRequests[4])) {
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean registerResult) {
            if(registerResult) {
                String[] reqs = new String[1];
                reqs[0] = alias;
                UserTask uTask = new UserTask();
                uTask.execute(reqs);
            }
            else {
                activity.onRegister(false);
            }
        }
    }

    private class UserTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... UserRequests) {
            model.setViewedUser(UserRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            model.setCurrentUser(model.getViewedUser());
            String[] reqs = new String[1];
            reqs[0] = alias;
            FeedTask fTask = new FeedTask();
            fTask.execute(reqs);
        }

    }

    private class FeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FeedRequests) {
            model.setViewedUserFeed(FeedRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean feedResult) {

            String[] reqs = new String[1];
            reqs[0] = alias;
            StoryTask sTask = new StoryTask();
            sTask.execute(reqs);
        }

    }

    private class StoryTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... StoryRequests) {
            model.setViewedUserStory(StoryRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean storyResult) {

            String[] reqs = new String[1];
            reqs[0] = alias;
            FollowerTask fTask = new FollowerTask();
            fTask.execute(reqs);
        }

    }

    private class FollowerTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FollowRequests) {
            model.setViewedUserFollowers(FollowRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean followerResult) {
            String[] reqs = new String[1];
            reqs[0] = alias;
            FollowingTask fTask = new FollowingTask();
            fTask.execute(reqs);
        }

    }

    private class FollowingTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FollowRequests) {
            model.setViewedUserFollowing(FollowRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean followingResult) {
            activity.onRegister(true);
        }

    }

}
