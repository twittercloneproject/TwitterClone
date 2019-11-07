package com.example.twitterclone.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.User;
import com.example.twitterclone.presenter.FeedPresenter;
import com.example.twitterclone.uihelp.StatusesAdapter;
import com.example.twitterclone.uihelp.UsersAdapter;

import org.w3c.dom.Text;

import java.util.List;


public class FeedFragment extends Fragment {
    private FeedPresenter presenter;

    private Dialog myDialog;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        myDialog = new Dialog(getContext());
        presenter = new FeedPresenter(this);
        List<Status> arrayOfStatuses = presenter.getStatuses();
        StatusesAdapter adapter = new StatusesAdapter(getActivity(), arrayOfStatuses);
        final ListView listView = (ListView) view.findViewById(R.id.followingLV);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewStatus(view);
            }
        });

        return view;
    }

    public void viewStatus(View view) {
        myDialog.setContentView(R.layout.view_status);

        Button close = (Button) myDialog.findViewById(R.id.closeButton);

        List aliasIndexes;
        List hashIndexes;

        WebView profilePic = (WebView) myDialog.findViewById(R.id.profilePic);
        TextView aliasView = (TextView) myDialog.findViewById(R.id.alias);
        TextView dateView = (TextView) myDialog.findViewById(R.id.date);
        TextView messageView = (TextView) myDialog.findViewById(R.id.message);
        TextView t = view.findViewById(R.id.username);
        WebView uploadImage = (WebView) myDialog.findViewById(R.id.webImage);

        TextView d = view.findViewById(R.id.date);
        Status s = presenter.getStatus(t.getText().toString(), d.getText().toString());
        profilePic.getSettings().setLoadWithOverviewMode(true);
        profilePic.getSettings().setUseWideViewPort(true);
        profilePic.loadUrl(s.getProfilePic());
        if(!s.getUrl().equals("")) {
            uploadImage.getSettings().setLoadWithOverviewMode(true);
            uploadImage.getSettings().setUseWideViewPort(true);
            uploadImage.loadUrl(s.getUrl());
        }
        dateView.setText(s.getDate());

        String tmp;
        tmp = t.getText().toString();
        aliasIndexes = presenter.findAlias(tmp);
        SpannableString aliasSS = new SpannableString(tmp);
        int index1 = (int) aliasIndexes.get(0);
        int index2 = (int) aliasIndexes.get(1)  + 1;
        aliasSS.setSpan(new MyClickableAliasSpan(tmp), index1, index2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        aliasView.setText(aliasSS);
        aliasView.setMovementMethod(LinkMovementMethod.getInstance());
        Log.d("activity", "here");
/*
        t = view.findViewById(R.id.tweetMessage);
        tmp = t.getText().toString();
        aliasIndexes = presenter.findAlias(tmp);
        hashIndexes = presenter.findHash(tmp);
        SpannableString messageSS = new SpannableString(tmp);
        for(int i = 0; i < aliasIndexes.size(); ) {
            index1 = (int) aliasIndexes.get(i);
            index2 = (int) aliasIndexes.get(i+1)  + 1;
            messageSS.setSpan(new MyClickableAliasSpan(tmp.substring(index1, index2)), index1, index2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        messageView.setText(messageSS);
        messageView.setMovementMethod(LinkMovementMethod.getInstance());
        */


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.show();
    }

    public void onLinkSelected() {
        Intent i = new Intent(getContext(), UserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("story", true);
        i.putExtras(bundle);
        startActivity(i);
    }

    private class MyClickableAliasSpan extends ClickableSpan {
        private String alias;

        public MyClickableAliasSpan(String alias) {
            this.alias = alias;
        }

        @Override
        public void onClick(@NonNull View widget) {
            myDialog.dismiss();
            presenter.findUser(this.alias);
        }
    }

    private class MyClickableHashSpan extends ClickableSpan {
        private String hash;

        public MyClickableHashSpan(String hash) {
            this.hash = hash;
        }

        @Override
        public void onClick(@NonNull View widget) {
            myDialog.dismiss();

        }
    }


}