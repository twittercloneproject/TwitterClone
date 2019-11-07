package com.example.twitterclone.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.twitterclone.R;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.User;
import com.example.twitterclone.presenter.FollowerPresenter;
import com.example.twitterclone.uihelp.UsersAdapter;

import java.util.List;


public class FollowerFragment extends Fragment {
    private FollowerPresenter presenter;
    private Model model = Model.getInstance();

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follower, container, false);

        presenter = new FollowerPresenter();
        List<User> arrayOfUsers = presenter.getFollowers();
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