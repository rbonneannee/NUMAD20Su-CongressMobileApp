package com.cs5520.numad20su_congressmobile.database;

import java.util.ArrayList;
import java.util.List;


public class User {

    public final List<String> followedBillIds;
    public final List<String> followedCommitteeIds;
    public final List<String> followedMemberIds;
    public final List<String> followedTopicIds;
    public String uid;
    public String profileAddress;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User() {
        followedBillIds = new ArrayList<>();
        followedCommitteeIds = new ArrayList<>();
        followedMemberIds = new ArrayList<>();
        followedTopicIds = new ArrayList<>();
        profileAddress = "empty";
    }

    // Copy constructor to quickly return a copy of original
    public User(User original) {
        this.uid = original.uid;
        this.followedBillIds = original.followedBillIds;
        this.followedCommitteeIds = original.followedCommitteeIds;
        this.followedMemberIds = original.followedMemberIds;
        this.followedTopicIds = original.followedTopicIds;
        this.profileAddress = original.profileAddress;
    }
}