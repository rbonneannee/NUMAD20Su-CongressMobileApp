package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivitySettingsBinding;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
    setContentView(activitySettingsBinding.getRoot());
  }

  public void getImage() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"CONGMOB_PROFILE_PIC" + ".jpg"));
    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, RC_GET_IMAGE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case RC_GET_IMAGE:
        if (resultCode == RESULT_OK) {
          Bundle extras = data.getExtras();
          Log.e("URI",imageUri.toString());

          Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
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

  private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "CONGMOB_PROFILE_PIC";
    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
    );

    // Save a file: path for use with ACTION_VIEW intents
    currentPhotoPath = image.getAbsolutePath();
    return image;
  }

}