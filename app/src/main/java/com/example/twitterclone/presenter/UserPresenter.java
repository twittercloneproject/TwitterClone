package com.example.twitterclone.presenter;

import android.os.AsyncTask;

import com.example.twitterclone.activity.UserActivity;
import com.example.twitterclone.data.Alias;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.User;

import java.util.List;

public class UserPresenter {
    private Model model = Model.getInstance();
    private User currentUser;
    private User viewedUser;
    private UserActivity activity;
    private String alias;

    public UserPresenter(UserActivity activity) {
        this.activity = activity;
    }

    public void sendStatus(String username, String name, String message, String date,
                           List<String> hashtags, String profilePic, String attachment) {
        Status[] reqs = new Status[2];
        reqs[0] = new Status(name.substring(1), message, new Alias(username), date, attachment, profilePic);
        reqs[0].setHashtags(hashtags);
        SendStatusTask ssTask = new SendStatusTask();
        ssTask.execute(reqs);
    }

    public void updatePhoto(String username, String profilePic) {
        String[] reqs = new String[5];;
        reqs[0] = "a";
        reqs[1] = "a";
        reqs[2] = username;
        reqs[3] = "a";
        reqs[4] = profilePic;
        RegisterTask sTask = new RegisterTask();
        sTask.execute(reqs);
    }

    public String getAlias() {
        currentUser = model.getCurrentUser();
        return currentUser.getAlias().getUsername();
    }

    public boolean isCurrentUser() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        if(currentUser.getAlias().getUsername().equals(viewedUser.getAlias().getUsername())) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Integer> findHashIndexes(String message) {
        return model.searchHashTags(message);
    }

    public void follow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        String[] reqs = new String[2];
        reqs[0] = currentUser.getAlias().getUsername();
        reqs[1] = viewedUser.getAlias().getUsername();
        FollowTask fTask = new FollowTask();
        fTask.execute(reqs);
    }

    public void unfollow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        String[] reqs = new String[2];
        reqs[0] = currentUser.getAlias().getUsername();
        reqs[1] = viewedUser.getAlias().getUsername();
        UnFollowTask ufTask = new UnFollowTask();
        ufTask.execute(reqs);
    }

    public void search(String alias) {
        this.alias = alias;
        String[] reqs = new String[1];
        reqs[0] = alias;
        UserTask uTask = new UserTask();
        uTask.execute(reqs);

    }

    public void isFollowing() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        String[] reqs = new String[2];
        reqs[0] = currentUser.getAlias().getUsername();
        reqs[1] = viewedUser.getAlias().getUsername();
        IsFollowingTask ifTask = new IsFollowingTask();
        ifTask.execute(reqs);
    }

    public String getUserImage() {
        viewedUser = model.getViewedUser();
        if(viewedUser == null) {
            viewedUser = model.getCurrentUser();
        }
        return viewedUser.getUrl();
    }

    private class UserTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... UserRequests) {
            return model.setViewedUser(UserRequests[0]);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                String[] reqs = new String[1];
                reqs[0] = alias;
                FeedTask fTask = new FeedTask();
                fTask.execute(reqs);
            }
            else {
                activity.onSearch(false);
            }
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
            activity.onSearch(true);
        }

    }

    private class FollowTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... usernames) {
            model.follow(usernames[0], usernames[1]);
            return true;
        }
    }

    private class UnFollowTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... usernames) {
            model.unfollow(usernames[0], usernames[1]);
            return true;
        }
    }

    private class IsFollowingTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... usernames) {
            return model.isFollowing(usernames[0], usernames[1]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            activity.onIsFollowing(aBoolean);
        }
    }

    private class SendStatusTask extends AsyncTask<Status, Void, Boolean> {

        @Override
        protected Boolean doInBackground(com.example.twitterclone.data.Status... statuses) {
            model.sendStatus(statuses[0].getAlias().getUsername(), statuses[0].getName(), statuses[0].getMessage(), statuses[0].getDate(),
                    statuses[0].getHashtags(), statuses[0].getProfilePic(), statuses[0].getUrl());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            activity.onSent(true);
        }
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
    }

}
