package com.example.twitterclone.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.twitterclone.R;
import com.example.twitterclone.data.User;
import com.example.twitterclone.presenter.FollowingPresenter;
import com.example.twitterclone.uihelp.UsersAdapter;

import java.util.List;


public class FollowingFragment extends Fragment {

    private FollowingPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        presenter = new FollowingPresenter();
        List<User> arrayOfUsers = presenter.getFollowing();
        UsersAdapter adapter = new UsersAdapter(getActivity(), arrayOfUsers);
        ListView listView = (ListView) view.findViewById(R.id.followingLV);
        listView.setAdapter(adapter);

        Button nextPageButton = view.findViewById(R.id.nextPageButton);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}