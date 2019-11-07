package com.example.twitterclone.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Spannable;
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
import com.example.twitterclone.presenter.StoryPresenter;
import com.example.twitterclone.uihelp.StatusesAdapter;
import com.example.twitterclone.uihelp.WebViewPicture;

import java.util.List;


public class StoryFragment extends Fragment {
    private StoryPresenter presenter;

    private Dialog myDialog;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        myDialog = new Dialog(getContext());
        presenter = new StoryPresenter();
        List<Status> arrayOfStatuses = presenter.getStatuses();
        StatusesAdapter adapter = new StatusesAdapter(getActivity(), arrayOfStatuses);
        ListView listView = (ListView) view.findViewById(R.id.followingLV);
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
        List indexs;
        WebView profilePic = (WebView) myDialog.findViewById(R.id.profilePic);
        TextView aliasView = (TextView) myDialog.findViewById(R.id.alias);
        TextView dateView = (TextView) myDialog.findViewById(R.id.date);
        TextView message = (TextView) myDialog.findViewById(R.id.message);
        WebView uploadImage = (WebView) myDialog.findViewById(R.id.webImage);
        TextView t = view.findViewById(R.id.username);
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

        message.setMovementMethod(LinkMovementMethod.getInstance());
        aliasView.setMovementMethod(LinkMovementMethod.getInstance());

        String tmp;
        tmp = t.getText().toString();
        indexs = presenter.searchMessage(tmp);
        aliasView.setText(tmp, TextView.BufferType.SPANNABLE);
        final Spannable mySpannable = (Spannable)aliasView.getText();
        ClickableSpan myClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                presenter.findUser(mySpannable.toString());
                Intent i = new Intent(getActivity(), UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("story", true);
                i.putExtras(bundle);
                startActivity(i);
            }
        };
        for(int i = 0; i < indexs.size(); i=i+2) {
            int index1 = (int) indexs.get(i);
            int index2 = (int) indexs.get(i+1);
            mySpannable.setSpan(myClickableSpan, index1, index2+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        t = view.findViewById(R.id.tweetMessage);
        tmp = t.getText().toString();
        indexs = presenter.searchMessage(tmp);
        List indexs1 = presenter.findHash(tmp);

        message.setText(tmp, TextView.BufferType.SPANNABLE);
        final Spannable mySpannable2 = (Spannable)message.getText();
        ClickableSpan myClickableSpan1 =  new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }
        };
        ClickableSpan myClickableSpan2 = new ClickableSpan() {

            @Override
            public void onClick(@NonNull View widget) {
                TextView t = (TextView) widget;
                String tmp = t.getText().toString();
                List indexs = presenter.searchMessage(tmp);
                Log.d("activity", tmp.substring((int)indexs.get(0), (int)indexs.get(1)+1));
                presenter.findUser(tmp.substring((int)indexs.get(0), (int)indexs.get(1)+1));
                Intent i = new Intent(getActivity(), UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("story", true);
                i.putExtras(bundle);
                startActivity(i);
            }
        };
        ClickableSpan myClickableSpan3 = new ClickableSpan() {

            @Override
            public void onClick(@NonNull View widget) {
                TextView t = (TextView) widget;
                String tmp = t.getText().toString();
                List indexs = presenter.searchMessage(tmp);
                presenter.findUser(tmp.substring((int)indexs.get(2), (int)indexs.get(3)+1));
                Intent i = new Intent(getActivity(), UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("story", true);
                i.putExtras(bundle);
                startActivity(i);
            }
        };

        for(int i = 0; i < indexs.size(); i=i+4) {

            int index1 = (int) indexs.get(i);
            int index2 = (int) indexs.get(i+1);
            mySpannable2.setSpan(myClickableSpan2, index1, index2+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(indexs.size() > 2) {
                index1 = (int) indexs.get(i+2);
                index2 = (int) indexs.get(i+3);
                mySpannable2.setSpan(myClickableSpan3, index1, index2+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if(indexs1.size() > 1) {
                mySpannable2.setSpan(myClickableSpan1, (int) indexs1.get(0), (int) indexs1.get(1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });


        myDialog.show();
    }
}