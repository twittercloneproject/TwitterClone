package com.example.twitterclone.uihelp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twitterclone.R;
import com.example.twitterclone.data.Status;

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
        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profilePicImageView) ;
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView alias = (TextView) convertView.findViewById(R.id.usernameDate);
        TextView message = (TextView) convertView.findViewById(R.id.tweetMessage);
        ImageView uploadimage = (ImageView) convertView.findViewById((R.id.uploadImage));
        // Populate the data into the template view using the data object
        profilePic.setImageResource((status.getImageID()));
        name.setText(status.getName());
        alias.setText(status.getAlias().getUsername());
        message.setText(status.getMessage());
        if(status.getImageID1() != 0) {
            uploadimage.setImageResource(status.getImageID1());
        }

        // Return the completed view to render on screen
        return convertView;
    }




}
