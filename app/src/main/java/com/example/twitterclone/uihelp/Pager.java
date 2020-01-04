package com.example.twitterclone.uihelp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.twitterclone.activity.FeedFragment;
import com.example.twitterclone.activity.FollowerFragment;
import com.example.twitterclone.activity.FollowingFragment;
import com.example.twitterclone.activity.StoryFragment;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FeedFragment feed = new FeedFragment();
                return feed;
            case 1:
                StoryFragment story = new StoryFragment();
                return story;
            case 2:
                FollowingFragment following = new FollowingFragment();

                return following;
            case 3:
                FollowerFragment follower = new FollowerFragment();

                return follower;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}