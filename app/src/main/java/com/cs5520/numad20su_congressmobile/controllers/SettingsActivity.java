package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivityMainBinding;
import com.cs5520.numad20su_congressmobile.databinding.ActivitySettingsBinding;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_GET_IMAGE = 101;
    private ActivitySettingsBinding activitySettingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
    }

    public void getImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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

}