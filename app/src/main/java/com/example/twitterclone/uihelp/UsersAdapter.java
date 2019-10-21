package com.example.twitterclone.uihelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twitterclone.R;
import com.example.twitterclone.data.User;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.follow_item, parent, false);
        }
        // Lookup view for data population
        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profilePicImageView) ;
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView alias = (TextView) convertView.findViewById(R.id.usernameDate);
        // Populate the data into the template view using the data object
        profilePic.setImageResource(user.getImageID());
        name.setText(user.getFirstName() + " " + user.getLastName());
        alias.setText(user.getAlias().getUsername());
        // Return the completed view to render on screen
        return convertView;
    }
}