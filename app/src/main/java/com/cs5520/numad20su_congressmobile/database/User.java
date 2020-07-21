package com.cs5520.numad20su_congressmobile.database;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.content.models.Member;

import java.util.ArrayList;

public class User {

    private String registrationToken;
    private String username;
    private String imageURL;

    private ArrayList<String> likedTopics;
    private ArrayList<Bill> likedBills;
    private ArrayList<Member> membersOfUserDistrict;


}
