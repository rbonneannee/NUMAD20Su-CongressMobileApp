package com.cs5520.numad20su_congressmobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

// TODO Put link to https://icons8.com/license in Settings or About
// TODO Use Cloud Storage for Firebase to upload user photo
// TODO Follow/Unfollow bills/members/committees/subjects
// TODO Present in MyFeed and allow updates through Settings
// TODO Grab content for both House and Senate, for each tab
// TODO Respond to clicks of actions in action bar
// TODO Put in a working search bar

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener {

    private static final int RC_GET_IMAGE = 101;
    private static final int RC_SIGN_IN = 102;

    private ActivityMainBinding activityMainBinding;
    private FirebaseAuth firebaseAuthInstance;
    private AuthUI authUiInstance;

    public void getImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, RC_GET_IMAGE);
        }
    }

    public void openSettings() {
        Intent openSettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(openSettingsIntent);
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_GET_IMAGE:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    (activityMainBinding.profilePicture).setImageBitmap(bitmap);
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
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        this.firebaseAuthInstance = firebaseAuth;
        updateUI(firebaseAuth.getCurrentUser());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authUiInstance = AuthUI.getInstance();
        firebaseAuthInstance = FirebaseAuth.getInstance();
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
        int i = view.getId();
        switch (view.getId()) {
            case R.id.profile_picture:
                //getImage();
                openSettings();
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

    private void updateUI(FirebaseUser user) {
        // TODO Update username and display it
        // TODO Add error checking for (empty) username
        // TODO Use identification token from IDP to store data in database
        if (user != null) {
            activityMainBinding.layoutSignin.setVisibility(View.GONE);
            activityMainBinding.layoutMain.setVisibility(View.VISIBLE);
        } else {
            activityMainBinding.layoutSignin.setVisibility(View.VISIBLE);
            activityMainBinding.layoutMain.setVisibility(View.GONE);
        }
    }


}
