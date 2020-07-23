package com.cs5520.numad20su_congressmobile.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Click listeners
        binding.buttonSignIn.setOnClickListener(this);


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
                    getString(R.string.heading_members),
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
    public void onClick(View view) {
        // TODO Add handler for profile image click
        // TODO Give focus to enter button so that you don't need to hide the keyboard
        int i = view.getId();
        if (i == R.id.buttonSignIn) {
            signInAnonymously();
        }
    }

    private void signInAnonymously() {
        // Sign in anonymously. Authentication is required to read or write from Firebase Storage.
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
        // TODO Update username and display it
        // TODO Add error checking for (empty) username
        // TODO Update registrationToken and authToken in database
        // Signed in or Signed out
        if (user != null) {
            binding.layoutSignin.setVisibility(View.GONE);
            binding.layoutMain.setVisibility(View.VISIBLE);
        } else {
            binding.layoutSignin.setVisibility(View.VISIBLE);
            binding.layoutMain.setVisibility(View.GONE);
        }
    }
}
