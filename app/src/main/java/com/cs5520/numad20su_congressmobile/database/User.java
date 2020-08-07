package com.cs5520.numad20su_congressmobile.database;

import java.util.ArrayList;
import java.util.List;

// TODO Store image in Firebase Cloud Storage
// TODO Display usernmame as first part of email (in settings screen)

public class User {

  public String uid;
  public List<String> followedBillIds;
  public List<String> followedCommitteeIds;
  public List<String> followedMemberIds;
  public List<String> followedTopicIds;

  // Default constructor required for calls to DataSnapshot.getValue(User.class)
  public User() {
    followedBillIds = new ArrayList<>();
    followedCommitteeIds = new ArrayList<>();
    followedMemberIds = new ArrayList<>();
    followedTopicIds = new ArrayList<>();
  }

  // Copy constructor to quickly return a copy of original
  public User(User original) {
    this.uid = original.uid;
    this.followedBillIds = original.followedBillIds;
    this.followedCommitteeIds = original.followedCommitteeIds;
    this.followedMemberIds = original.followedMemberIds;
    this.followedTopicIds = original.followedTopicIds;
  }
}