package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO Put link to https://icons8.com/license in Settings or About
// TODO Use Cloud Storage for Firebase to upload user photo
// TODO Follow/Unfollow bills/members/committees/subjects
// TODO Present in MyFeed and allow updates through Settings
// TODO Grab content for both House and Senate, for each tab
// TODO Respond to clicks of actions in action bar
// TODO Put in a working search bar

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
    new User(this.currentUser);
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

  public void openSettings() {
    Intent openSettingsIntent = new Intent(this, SettingsActivity.class);
    startActivity(openSettingsIntent);
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
