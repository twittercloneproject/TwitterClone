package com.example.twitterclone.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitterclone.activity.SignInActivity;
import com.example.twitterclone.data.Model;


public class SignInPresenter {

    private Model model = Model.getInstance();
    private String alias;
    private SignInActivity activity;

    public void login(String alias, String password, SignInActivity act) {
        this.alias = alias;
        this.activity = act;
        String[] reqs = new String[2];
        reqs[0] = alias;
        reqs[1] = password;
        SignInTask sTask = new SignInTask();
        sTask.execute(reqs);
    }



    private class SignInTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... signInInfo) {
            if(model.signIn(signInInfo[0], signInInfo[1])) {
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean signInResult) {

            if(signInResult) {
                String[] reqs = new String[1];
                reqs[0] = alias;
                UserTask uTask = new UserTask();
                uTask.execute(reqs);
            }
            else {
                activity.onSignIn(false);
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
            activity.onSignIn(true);
        }

    }






}
