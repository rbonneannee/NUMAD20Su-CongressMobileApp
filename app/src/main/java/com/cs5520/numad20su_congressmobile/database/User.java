package com.cs5520.numad20su_congressmobile.database;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.content.models.Member;

import java.util.ArrayList;

public class User {

    private String registrationToken;
    private String authenticationToken;
    private String username;
    private String imageUrl;

    private ArrayList<Bill> likedBills;
    private ArrayList<Member> membersOfUserDistrict;


}
