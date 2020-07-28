package com.cs5520.numad20su_congressmobile.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

// TODO Put link to https://icons8.com/license in Settings or About

// TODO Use anonymous sign-in
// TODO Use Cloud Storage for Firebase to upload user photo
// TODO Follow/Unfollow bills/members/committees, present in MyFeed, update in Settings

// TODO For each tab, grab content for both House and Senate
// TODO Respond to clicks of actions in action bar
// TODO Put in a working search bar
// TODO Cancel requests onSwipe for the ViewPager so as not to hold up other tabs
// TODO     See "Cancel a request" at https://developer.android.com/training/volley/simple
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    ActivityMainBinding binding;
    private ImageView targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        targetImage = (ImageView) findViewById(R.id.profile_picture);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Click listeners
        binding.buttonSignIn.setOnClickListener(this);

        // Add key listener for the enter button for the username entry
        TextInputEditText mUsernameFld = findViewById(R.id.inputField_username);
        mUsernameFld.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard(view);
                    signInAnonymously();
                }
                return false;
            }
        });

        // Create the adapter that will return a fragment for each section
        FragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            private final Fragment[] mFragments = new Fragment[]{
                    new MyFeedFragment(),
                    new BillsFragment(),
                    new CommitteesFragment(),
                    new MembersFragment()
            };
            private final String[] mFragmentNames = new String[]{
                    getString(R.string.heading_my_feed),
                    getString(R.string.heading_bills),
                    getString(R.string.heading_committees),
                    getString(R.string.heading_members)
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        binding.container.setAdapter(mPagerAdapter);
        binding.tabs.setupWithViewPager(binding.container);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.manage_profile) {
            //Toast.makeText(this, "braff", Toast.LENGTH_LONG).show();
            openSettings(this.getCurrentFocus());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    public void openSettings(View view) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            targetImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View view) {
        // TODO Add handler for profile image click
        // TODO Give focus to enter button so that you don't need to hide the keyboard
        int i = view.getId();
        if (i == R.id.buttonSignIn) {
            signInAnonymously();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void signInAnonymously() {
        // Sign in anonymously. Authentication is required to read or write from Firebase Storage.
        // TODO Save user to database
        mAuth.signInAnonymously()
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "signInAnonymously:SUCCESS");
                        updateUI(authResult.getUser());
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        // TODO A new user is created with each install, is this okay?
        // TODO Update username and display it
        // TODO Add error checking for (empty) username
        // TODO Update registrationToken and authToken in database
        if (user != null) {
            binding.layoutSignin.setVisibility(View.GONE);
            binding.layoutMain.setVisibility(View.VISIBLE);
        } else {
            binding.layoutSignin.setVisibility(View.VISIBLE);
            binding.layoutMain.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Remove this code for final version of app
        super.onDestroy();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Objects.requireNonNull(auth.getCurrentUser()).delete();
    }
}
