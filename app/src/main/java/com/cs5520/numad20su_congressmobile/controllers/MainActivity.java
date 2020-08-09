package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.database.User;
import com.cs5520.numad20su_congressmobile.databinding.ActivityMainBinding;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MainFragmentPagerAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Use Cloud Storage for Firebase to upload user photo
// TODO Get all search bars working
// TODO Make search bars so that enter from soft keyboard initiates search
// TODO Put a loading icon on the MyFeed page or figure out how to pull data faster
// TODO Determine if GetRequestType.FILTER is still needed

public class MainActivity extends AppCompatActivity implements
    View.OnClickListener,
    FirebaseAuth.AuthStateListener,
    FollowInterface {

    private static final int RC_GET_IMAGE = 101;
    private static final int RC_SIGN_IN = 102;
    private ActivityMainBinding activityMainBinding;
    private AuthUI authUiInstance;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuthInstance;
    private User currentUser;

    @Override
    public void follow(TYPE type, String id) {
        switch (type) {
            case Bill:
                this.currentUser.followedBillIds.add(id);
                break;
            case Committee:
                this.currentUser.followedCommitteeIds.add(id);
                break;
            case Member:
                this.currentUser.followedMemberIds.add(id);
                break;
            case Topic:
                this.currentUser.followedTopicIds.add(id);
                break;
        }
        databaseReference.child("users").child(this.currentUser.uid).setValue(this.currentUser);
    }

    @Override
    public List<String> following(TYPE type) {
        List<String> result = new ArrayList<>();
        switch (type) {
            case Bill:
                if (currentUser != null) {
                    result.addAll(currentUser.followedBillIds);
                }

                break;
            case Committee:
                result.addAll(currentUser.followedCommitteeIds);
                break;
            case Member:
                result.addAll(currentUser.followedMemberIds);
                break;
        }
        return result;
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
                    // Get cloud storage instance
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    StorageReference storageRef = FirebaseStorage
                        .getInstance()
                        .getReference("photos/")
                        .child(uid);

                    // Set image as profile picture
                    Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

                    (activityMainBinding.profilePicture).setImageBitmap(bitmap);

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
                        updateUI(firebaseAuthInstance.getCurrentUser());
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    });

                }
                break;
            case RC_SIGN_IN:
                if (resultCode != RESULT_OK) { // Sign in failed
                    Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if ((fragment instanceof BillsFragment) || (fragment instanceof CommitteesFragment) ||
            (fragment instanceof MembersFragment) || (fragment instanceof MyFeedFragment)) {
            FollowTrigger triggerFragment = (FollowTrigger) fragment;
            triggerFragment.registerListener(this);
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        this.firebaseAuthInstance = firebaseAuth;
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        this.currentUser = new User();

        if (firebaseUser != null) {
            currentUser.uid = firebaseUser.getUid();
            databaseReference.child("users").child(currentUser.uid).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User usr = snapshot.getValue(User.class);
                        if (usr != null) {
                            currentUser = usr;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("onAuthStateChanged", "onCancelled", error.toException());
                    }
                });

        }

        updateUI(firebaseUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Firebase instances
        authUiInstance = AuthUI.getInstance();
        firebaseAuthInstance = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Set up view bindings
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setSupportActionBar(activityMainBinding.myToolbar);

        // Set up ViewPager
        FragmentPagerAdapter adapter = new MainFragmentPagerAdapter(
            this,
            getSupportFragmentManager(),
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        activityMainBinding.container.setAdapter(adapter);
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.container);

        // Set listeners
        activityMainBinding.profilePicture.setOnClickListener(this);
        activityMainBinding.buttonSignIn.setOnClickListener(this);
        firebaseAuthInstance.addAuthStateListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_picture:
                getImage();
                break;
            case R.id.buttonSignIn:
                startSignIn();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buttonSignOut) {
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(firebaseAuthInstance.getCurrentUser());
    }

    private void signOut() {
        authUiInstance.signOut(this);
    }

    private void startSignIn() {
        startActivityForResult(authUiInstance.createSignInIntentBuilder().build(), RC_SIGN_IN);
    }

    @Override
    public void unfollow(TYPE type, String id) {
        switch (type) {
            case Bill:
                this.currentUser.followedBillIds.remove(id);
                break;
            case Committee:
                this.currentUser.followedCommitteeIds.remove(id);
                break;
            case Member:
                this.currentUser.followedMemberIds.remove(id);
                break;
            case Topic:
                this.currentUser.followedTopicIds.remove(id);
                break;
        }
        databaseReference.child("users").child(this.currentUser.uid).setValue(this.currentUser);
        new User(this.currentUser);
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {

            // Get a reference to where the user's profile pic would be stored
            StorageReference imgRef = FirebaseStorage.getInstance().getReference()
                .child("photos/" + user.getUid());

            // Download the profile pic, if the user has one, into the image view
            final long ONE_MEGABYTE = 1024 * 1024;
            imgRef.getBytes(5 * ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                // Data for "images/island.jpg" is returns, use this as needed
                if (bytes.length > 0) {
                    Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    activityMainBinding.profilePicture.setImageBitmap(image);
                }
            });

            // Hide signin layout and show the app
            activityMainBinding.layoutSignin.setVisibility(View.GONE);
            activityMainBinding.layoutMain.setVisibility(View.VISIBLE);

        } else {

            activityMainBinding.layoutSignin.setVisibility(View.VISIBLE);
            activityMainBinding.layoutMain.setVisibility(View.GONE);

        }
    }
}
