package com.example.twitterclone.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.presenter.UserPresenter;
import com.example.twitterclone.uihelp.Pager;
import com.example.twitterclone.uihelp.WebViewPicture;


import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views
public class UserActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private UserPresenter presenter;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    private Dialog myDialog;
    private WebView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("user activity", "onCreate() called");
        setContentView(R.layout.activity_user);
        Intent intentExtras = getIntent();

        presenter = new UserPresenter(UserActivity.this);
        myDialog = new Dialog(this);
        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("user activity", "toolbar created");


        this.profilePic = findViewById(R.id.profilePic);
        profilePic.getSettings().setLoadWithOverviewMode(true);
        profilePic.getSettings().setUseWideViewPort(true);
        profilePic.loadUrl(presenter.getUserImage());
        profilePic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectImage(UserActivity.this);
                return false;
            }
        });

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

        WebView profilePic = (WebView) myDialog.findViewById(R.id.profilePic);
        TextView aliasView = (TextView) myDialog.findViewById(R.id.alias);
        TextView dateView = (TextView) myDialog.findViewById(R.id.date);
        LocalDateTime date = LocalDateTime.now();
        dateView.setText(date.toString());

        final WebView uploadImage = (WebView) myDialog.findViewById(R.id.uploadImage);

        profilePic.getSettings().setLoadWithOverviewMode(true);
        profilePic.getSettings().setUseWideViewPort(true);
        profilePic.loadUrl(presenter.getUserImage());
        CharSequence alias = presenter.getAlias();
        aliasView.setText(alias);
        uploadImage.setWebViewClient(new WebViewPicture());

        uploadImage.getSettings().setLoadWithOverviewMode(true);
        uploadImage.getSettings().setUseWideViewPort(true);
        uploadImage.loadUrl("data:image/png;base64,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");
        uploadImage.getSettings().setLoadsImagesAutomatically(true);
        uploadImage.getSettings().setJavaScriptEnabled(true);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                presenter.search(searchBar.getText().toString());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void onSearch(boolean success) {
        if(success) {
            Intent i = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "alias does not exist", Toast.LENGTH_SHORT).show();
        }
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
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        String imgageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        String dataURL= "data:image/png;base64," + imgageBase64;

                        this.profilePic.loadUrl(dataURL);
                        //  profilePic.setImageBitmap(selectedImage);
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
                                //      profilePic.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

}
