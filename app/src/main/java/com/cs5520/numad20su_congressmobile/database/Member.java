package com.cs5520.numad20su_congressmobile.database;

import android.widget.ArrayAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * https://projects.propublica.org/api-docs/congress-api/members/#get-a-specific-member
 */
public class Member {

    private String memberId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private Date dateOfBirth;
    private String gender;
    private String url;

    private String timesTopicsUrl;
    private String timesTag;
    private String govTrackId;
    private String cSpanId;
    private String voteSmartId;
    private String icpsrId;
    private String twitterAccount;
    private String facebookAccount;
    private String youtubeAccount;
    private String crpId;
    private String googleEntityId;
    private String rssUrl;

    private boolean inOffice;
    private String currentParty;
    private Date mostRecentVote;
    private Date lastUpdated;
    private ArrayList<Role> roles;










    // EXCLUDED FIELDS
    // private String id;


}
