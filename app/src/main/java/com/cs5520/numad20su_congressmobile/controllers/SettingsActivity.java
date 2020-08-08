package com.cs5520.numad20su_congressmobile.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivitySettingsBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

  private static final int RC_GET_IMAGE = 101;
  private ActivitySettingsBinding activitySettingsBinding;
  private String currentPhotoPath;
  private Uri imageUri;
  private ImageView profilePicture;
  private static final String TAG = "SettingsActivity";
  private String fileAddress;

//  private FirebaseAuth mAuth;
//  private Uri mDownloadUrl = null;
//  private Uri mFileUri = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
    setContentView(activitySettingsBinding.getRoot());
    profilePicture = findViewById(R.id.profile_picture);
    //mAuth = FirebaseAuth.getInstance();

    fileAddress="/storage/emulated/0/Android/data/com.cs5520.numad20su_congressmobile/files/Pictures/CONGMOB_PROFILE_PIC371868589069382354.jpg";

    if (!fileAddress.equals("empty") || fileAddress.equals("")) {
      Bitmap bitmap = BitmapFactory.decodeFile(fileAddress);
      (activitySettingsBinding.selectProfile).setImageBitmap(bitmap);
    }
  }

  public void getImage() {
    takePicture();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case RC_GET_IMAGE:
        if (resultCode == RESULT_OK) {
          Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
          Log.e(TAG, currentPhotoPath);
          //Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
          (activitySettingsBinding.selectProfile).setImageBitmap(bitmap);
        }
        break;
    }
  }

  @Override
  public void onClick(View view) {
    int i = view.getId();
    switch (view.getId()) {
      case R.id.select_profile:
        getImage();
        //openSettings();
        break;
    }
  }

  private void takePicture() {

    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      File imageFile = null;

      try {
        imageFile = getImageFile();
      }
      catch (IOException e) {
        e.printStackTrace();
      }

      if (imageFile != null) {
        imageUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", imageFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(takePictureIntent, RC_GET_IMAGE);
      }
    }
  }

  private File getImageFile() throws IOException {
    String imageFileName = "CONGMOB_PROFILE_PIC";
    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
    );
    currentPhotoPath = image.getAbsolutePath();
    return image;
  }
}