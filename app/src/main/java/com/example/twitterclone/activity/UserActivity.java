package com.example.twitterclone.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.presenter.UserPresenter;
import com.example.twitterclone.uihelp.Pager;

import java.util.Objects;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
public class UserActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private UserPresenter presenter;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    private Dialog myDialog;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("user activity", "onCreate() called");
        setContentView(R.layout.activity_user);
        Intent intentExtras = getIntent();

        presenter = new UserPresenter();
        myDialog = new Dialog(this);
        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("user activity", "toolbar created");

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Feed"));
        tabLayout.addTab(tabLayout.newTab().setText("Story"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Log.d("user activity", "tabs added");
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        //Adding adapter to pager
        viewPager.setAdapter(adapter);


        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
      /*  if(intentExtras.getExtras() != null) {
            if(intentExtras.getExtras().getBoolean("story")) {
                viewPager.setCurrentItem(1);
            }
        }*/


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_menu, menu);

        MenuItem item1 = menu.findItem(R.id.profilePic);
        item1.setIcon(presenter.getUserImage());

        MenuItem item = menu.findItem(R.id.followOrunfollow);
        if(presenter.isCurrentUser()) {
            item.setVisible(false);
        }
        else {
            item.setVisible(true);
            if(presenter.isFollowing()) {
                item.setTitle("Unfollow");
            }
            else {
                item.setTitle("Follow");
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.profilePic:
                if(presenter.isCurrentUser()) {
                    image = new ImageView(getApplicationContext());
                    selectImage(UserActivity.this);
                }
                break;
            case R.id.search_bar:
                searchAlias();
                break;
            case R.id.create_tweet:
                createStatus();
                break;
            case R.id.signOut:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
            case R.id.followOrunfollow:
                String type = item.getTitle().toString();
                if(type.equals("Follow")) {
                    item.setTitle("Unfollow");
                    presenter.follow();
                }
                else {
                    item.setTitle("Follow");
                    presenter.unfollow();
                }
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    private void createStatus() {
        myDialog.setContentView(R.layout.create_status);

        Button share = (Button) myDialog.findViewById(R.id.shareButton);
        Button close = (Button) myDialog.findViewById(R.id.closeButton);

        ImageView profilePic = (ImageView) myDialog.findViewById(R.id.profilePic);
        TextView aliasView = (TextView) myDialog.findViewById(R.id.alias);
        final ImageView uploadImage = (ImageView) myDialog.findViewById(R.id.uploadImage);

        profilePic.setImageResource(presenter.getUserImage());
        CharSequence alias = presenter.getAlias();
        aliasView.setText(alias);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = uploadImage;
                selectImage(UserActivity.this);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    private void searchAlias() {
        myDialog.setContentView(R.layout.search_bar);

        final ImageButton search = (ImageButton) myDialog.findViewById(R.id.search);
        Button close = (Button) myDialog.findViewById(R.id.closeButton);
        final EditText searchBar = (EditText) myDialog.findViewById(R.id.searchBar);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.search(searchBar.getText().toString())) {
                    Intent i = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "alias does not exist", Toast.LENGTH_SHORT).show();

                }
            }
        });
        myDialog.show();
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        if(!isFinishing()) {
            builder.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        image.setImageBitmap(selectedImage);

                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
