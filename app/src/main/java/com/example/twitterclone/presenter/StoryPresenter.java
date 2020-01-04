package com.example.twitterclone.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.twitterclone.activity.StoryFragment;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.Story;
import com.example.twitterclone.data.User;

import java.util.List;

public class StoryPresenter {
    private Model model = Model.getInstance();
    private StoryFragment fragment;
    private String alias;

    public StoryPresenter(StoryFragment fragment) {
        this.fragment = fragment;
    }

    public List<Status> getStatuses() {
        User viewedUser = model.getViewedUser();
        Story currentStory = viewedUser.getStory();
        return currentStory.getMyStatuses();
    }

    public Status getStatus(String alias, String date) {
        return model.getStatus(alias, date);
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

    public void getNextStoryPage() {
        String[] reqs = new String[1];
        reqs[0] = "next";
        StoryTask sTask = new StoryTask();
        sTask.execute(reqs);
    }

    public List findAlias(String message) {
        return model.searchAlias(message);
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
            if(StoryRequests[0].equals("next")) {
                model.getNextStoryPage();
                return false;
            }
            else {
                model.setViewedUserStory(StoryRequests[0]);
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean storyResult) {
            if(storyResult) {
                String[] reqs = new String[1];
                reqs[0] = alias;
                FollowerTask fTask = new FollowerTask();
                fTask.execute(reqs);
            }
            else {
                fragment.updateStory();
            }
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
