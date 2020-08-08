package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivitySettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
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
        if (requestCode == RC_GET_IMAGE) {
            if (resultCode == RESULT_OK) {
                // Get cloud storage instance
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                StorageReference storageRef = FirebaseStorage
                    .getInstance()
                    .getReference("photos/")
                    .child(uid);

                // Set image as profile picture
                Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

                // TODO Have this set the correct imageView
                // TODO Move this back to MainActivity (get rid of this activity)
                (activitySettingsBinding.selectProfile).setImageBitmap(bitmap);

                // Convert image data to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 100, baos);
                byte[] jpgData = baos.toByteArray();

                // Upload image to cloud storage
                Context context = this;
                UploadTask uploadTask = storageRef.putBytes(jpgData);
                uploadTask.addOnFailureListener(
                    exception -> Toast.makeText(context, "Failure", Toast.LENGTH_LONG)
                        .show()).addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                });

            }
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (view.getId() == R.id.select_profile) {
            getImage();
        }
    }

}