package com.example.twitterclone.uihelp;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twitterclone.R;
import com.example.twitterclone.data.Model;
import com.example.twitterclone.data.Status;
import com.example.twitterclone.data.User;

import java.util.List;

public class StatusesAdapter extends ArrayAdapter<Status> {
    public StatusesAdapter(Context context, List<Status> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Status status = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.status_item, parent, false);
        }
        // Lookup view for data population
        WebView profilePic = (WebView) convertView.findViewById(R.id.profilePicWebView) ;
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView alias = (TextView) convertView.findViewById(R.id.username);
        TextView date = (TextView)  convertView.findViewById(R.id.date);
        TextView message = (TextView) convertView.findViewById(R.id.tweetMessage);
        WebView uploadimage = (WebView) convertView.findViewById((R.id.webImage));
        // Populate the data into the template view using the data object
        profilePic.getSettings().setLoadWithOverviewMode(true);
        profilePic.getSettings().setUseWideViewPort(true);
        profilePic.loadUrl(status.getProfilePic());
        name.setText(status.getName());
        alias.setText(status.getAlias().getUsername());
        date.setText(status.getDate());
        message.setText(status.getMessage());
        if(!status.getUrl().equals("")) {
            uploadimage.getSettings().setLoadWithOverviewMode(true);
            uploadimage.getSettings().setUseWideViewPort(true);
            uploadimage.loadUrl(status.getUrl());
        }

        // Return the completed view to render on screen
        return convertView;
    }




}
