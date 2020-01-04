package com.example.twitterclone.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twitterclone.R;
import com.example.twitterclone.presenter.RegisterPresenter;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {

    private RegisterPresenter presenter;
    private Button registerButton;
    private Button uploadPicture;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private WebView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Register activity", "onCreate() called");
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter();

        profilePic = (WebView) findViewById(R.id.ProfilePictureWebView);
        uploadPicture = (Button) findViewById(R.id.EditProfilePicture);
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(RegisterActivity.this);
            }
        });

        registerButton = (Button) findViewById(R.id.SignUpButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
                final String firstName = firstNameEditText.getText().toString();
                lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
                final String lastName = lastNameEditText.getText().toString();
                usernameEditText = (EditText) findViewById(R.id.usernameEditText);
                final String username = usernameEditText.getText().toString();
                passwordEditText = (EditText) findViewById(R.id.passwordEditText);
                final String password = passwordEditText.getText().toString();

                presenter.register(firstName, lastName, username, password, profilePic.getUrl(), RegisterActivity.this);
            }

        });

    }


    public void onRegister(boolean success) {
        if(success) {
            Intent i = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
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
                        profilePic.loadUrl(dataURL);
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
