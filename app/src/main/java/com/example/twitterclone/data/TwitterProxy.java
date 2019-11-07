package com.example.twitterclone.data;

import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.twitterclone.api.TwitterCloneClient;
import com.example.twitterclone.api.model.FollowRequest;
import com.example.twitterclone.api.model.FollowResult;
import com.example.twitterclone.api.model.HashtagRequest;
import com.example.twitterclone.api.model.IsFollowingRequest;
import com.example.twitterclone.api.model.IsFollowingResult;
import com.example.twitterclone.api.model.RegisterRequest;
import com.example.twitterclone.api.model.RegisterResult;
import com.example.twitterclone.api.model.RequestByAlias;
import com.example.twitterclone.api.model.SendStatusRequest;
import com.example.twitterclone.api.model.SendStatusResult;
import com.example.twitterclone.api.model.SignInRequest;
import com.example.twitterclone.api.model.SignInResult;
import com.example.twitterclone.api.model.StatusRequest;
import com.example.twitterclone.api.model.StatusResult;
import com.example.twitterclone.api.model.StatusResultStatus;
import com.example.twitterclone.api.model.StatusesResult;
import com.example.twitterclone.api.model.StatusesResultStatusesItem;
import com.example.twitterclone.api.model.UnFollowRequest;
import com.example.twitterclone.api.model.UnFollowResult;
import com.example.twitterclone.api.model.UserResult;
import com.example.twitterclone.api.model.UserResultUser;
import com.example.twitterclone.api.model.UsersResult;
import com.example.twitterclone.api.model.UsersResultUsersItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TwitterProxy {

    private TwitterCloneClient client;


    public TwitterProxy() {
        ApiClientFactory factory = new ApiClientFactory();
        this.client = factory.build(TwitterCloneClient.class);
    }

    public User getUser(String alias) {
        RequestByAlias request = new RequestByAlias();
        request.setAlias(alias);
        UserResult result = this.client.getuserPost(request);
        UserResultUser rUser = result.getUser();
        User user = new User(rUser.getFirstName(), rUser.getLastName(), new Alias(rUser.getAlias()), rUser.getUrlPicture());
        return user;
    }

    public Status getStatus(int id) {
        StatusRequest request = new StatusRequest();
        request.setId(id);
        StatusResult result = this.client.getstatusPost(request);
        StatusResultStatus rStatus = result.getStatus();
        Status status = new Status(rStatus.getName(),rStatus.getMessage(),new Alias(rStatus.getAlias()),rStatus.getDate(),rStatus.getUrlPicture(),rStatus.getId(),rStatus.getProfilePic());
        return status;
    }

    public String signIn(String alias, String password) {
        SignInRequest request = new SignInRequest();
        request.setAlias(alias);
        request.setPassword(password);
        SignInResult result = this.client.signinPost(request);
        return result.getMessage();
    }

    public String register(String firstName, String lastName, String alias, String password, String profilePic) {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setAlias(alias);
        request.setUrl(profilePic);
        request.setPassword(password);
        RegisterResult result = client.registerPost(request);
        return result.getMessage();
    }

    public String follow(String currentAlias, String otherAlias) {
        FollowRequest request = new FollowRequest();
        request.setCurrentAlias(currentAlias);
        request.setOtherAlias(otherAlias);
        FollowResult result = client.followuserPost(request);
        return result.getMessage();
    }

    public String unFollow(String currentAlias, String otherAlias) {
        UnFollowRequest request = new UnFollowRequest();
        request.setCurrentAlias(currentAlias);
        request.setOtherAlias(otherAlias);
        UnFollowResult result = client.unfollowuserPost(request);
        return result.getMessage();
    }

    public boolean isFollowing(String currentAlias, String otherAlias) {
        IsFollowingRequest request = new IsFollowingRequest();
        request.setCurrentAlias(currentAlias);
        request.setOtherAlias(otherAlias);
        IsFollowingResult result = client.isfollowingPost(request);
        return result.getIsFollowing();
    }

    public List<User> getFollowers(String alias) {
        RequestByAlias request = new RequestByAlias();
        request.setAlias(alias);
        UsersResult result = client.getfollowersPost(request);
        return this.createUsers(result.getUsers());
    }

    public List<User> getFollowing(String alias) {
        RequestByAlias request = new RequestByAlias();
        request.setAlias(alias);
        UsersResult result = client.getfollowingPost(request);
        return this.createUsers(result.getUsers());
    }

    public Feed getFeed(String alias) {
        RequestByAlias request = new RequestByAlias();
        request.setAlias(alias);
        StatusesResult result = client.getfeedPost(request);
        Feed feed = new Feed();
        feed.setStatuses(this.createStatuses(result.getStatuses()));
        return feed;
    }

    public Story getStory(String alias) {
        RequestByAlias request = new RequestByAlias();
        request.setAlias(alias);
        StatusesResult result = client.getstoryPost(request);
        Story story = new Story();
        story.setMyStatuses(this.createStatuses(result.getStatuses()));
        return story;
    }

    public List<Status> getHashtag(String hashtag) {
        HashtagRequest request = new HashtagRequest();
        request.setHashtag(hashtag);
        StatusesResult result = client.gethashtagsPost(request);
        return this.createStatuses(result.getStatuses());
    }

    public String sendStatus(String name, String alias, String message, String date, String urlPicture, String profielPic, List<String> hashtags) {
        SendStatusRequest request = new SendStatusRequest();
        request.setName(name);
        request.setAlias(alias);
        request.setDate(date);
        request.setMessage(message);
        request.setHashtags(hashtags);
        request.setProfilePic(profielPic);
        request.setUrlPicture(urlPicture);
        SendStatusResult result = client.sendstatusPost(request);
        return result.getMessage();
    }

    private List<User> createUsers(List<UsersResultUsersItem> items) {
        List<User> followers = new ArrayList<User>();
        User u;
        for(int i = 0; i < items.size(); i++) {
            u = new User(items.get(i).getFirstName(),items.get(i).getLastName(),new Alias(items.get(i).getAlias()),items.get(i).getUrlPicture());
            followers.add(u);
        }
        return followers;
    }

    private List<Status> createStatuses(List<StatusesResultStatusesItem> items) {
        List<Status> statuses = new ArrayList<Status>();
        Status s;
        for(int i = 0; i < items.size(); i++) {
            s = new Status(items.get(i).getName(), items.get(i).getMessage(), new Alias(items.get(i).getAlias()), items.get(i).getDate(), items.get(i).getUrlPicture(), items.get(i).getId(),items.get(i).getProfilePic());
            s.setHashtags(items.get(i).getHashtags());
            statuses.add(s);
        }
        return statuses;
    }
}
