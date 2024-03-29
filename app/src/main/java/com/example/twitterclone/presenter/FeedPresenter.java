package com.example.twitterclone.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitterclone.activity.FeedFragment;
import com.example.twitterclone.data.Feed;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.User;

import java.util.List;

public class FeedPresenter {
    private Model model = Model.getInstance();
    private String alias;
    private FeedFragment fragment;

    public FeedPresenter(FeedFragment fragment) {
        this.fragment = fragment;
    }

    public List<Status> getStatuses() {
        User viewedUser = model.getViewedUser();
        Feed currentFeed = viewedUser.getFeed();
        return currentFeed.getStatuses();
    }

    public List<Integer> findAlias(String message) {
        return model.searchAlias(message);
    }

    public List<Status> getHashtagStatuses() {
        return model.getHashtagStatuses();
    }

    public void setHashtagStatuses(String hashtag) {
        String[] reqs = new String[1];
        reqs[0] = hashtag;
        HashtagTask hTask = new HashtagTask();
        hTask.execute(reqs);
    }

    public Status getStatus(String alias, String date) {
        Log.d("activity", alias);
        Log.d("activity", date);
        return model.getStatus(alias, date);
    }

    public void findUser(String alias) {
        String[] reqs = new String[1];
        this.alias = alias;
        reqs[0] = alias;
        UserTask uTask = new UserTask();
        uTask.execute(reqs);
    }

    public List findHash(String message) {
        return model.searchHashTags(message);
    }

    public void getNextFeedPage() {
        String[] reqs = new String[1];
        reqs[0] = "next";
        FeedTask fTask = new FeedTask();
        fTask.execute(reqs);
    }

    public void getNexthashtagPage(String hashtag) {
        String[] reqs = new String[2];
        reqs[0] = "next";
        reqs[1] = hashtag;
        HashtagTask hTask = new HashtagTask();
        hTask.execute(reqs);
    }

    private class UserTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... UserRequests) {
            model.setViewedUser(UserRequests[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            String[] reqs = new String[1];
            reqs[0] = alias;
            FeedTask fTask = new FeedTask();
            fTask.execute(reqs);
        }

    }

    private class FeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... FeedRequests) {
            if(FeedRequests[0].equals("next")) {
                model.getNextFeedPage();
                return false;
            }
            else {
                model.setViewedUserFeed(FeedRequests[0]);
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean feedResult) {
            if(feedResult) {
                String[] reqs = new String[1];
                reqs[0] = alias;
                StoryTask sTask = new StoryTask();
                sTask.execute(reqs);
            }
            else {
                fragment.updateFeed();
            }
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
            fragment.onAliasSelected();
        }

    }

    private class HashtagTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... hashtags) {
            if(hashtags[0].equals("next")) {
                model.getNextHashtagPage(hashtags[1]);
                return false;
            }
            else {
                return model.setHashtagStatuses(hashtags[0]);
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                Log.d("activity", "hashtag post execute");
                fragment.onHashtagSelected();
            }
            else {
                fragment.updateHashtags();
            }
        }

    }
}
