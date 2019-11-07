package com.example.twitterclone.presenter;

import android.os.AsyncTask;

import com.example.twitterclone.activity.UserActivity;
import com.example.twitterclone.data.Model;
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

    public void follow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        viewedUser.addFollower(currentUser);
        currentUser.addFollowing(viewedUser);
    }

    public void unfollow() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        viewedUser.removeFollower(currentUser);
        currentUser.removeFollowing(viewedUser);
    }

    public void search(String alias) {
        this.alias = alias;
        String[] reqs = new String[1];
        reqs[0] = alias;
        UserTask uTask = new UserTask();
        uTask.execute(reqs);

    }

    public boolean isFollowing() {
        currentUser = model.getCurrentUser();
        viewedUser = model.getViewedUser();
        List<User> following = currentUser.getFollowings();
        for(int i = 0; i < following.size(); i++) {
            if(following.get(i).getAlias().getUsername().equals(viewedUser.getAlias().getUsername())) {
                return true;
            }
        }
        return false;
    }

    public String getUserImage() {
        viewedUser = model.getViewedUser();
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

}
