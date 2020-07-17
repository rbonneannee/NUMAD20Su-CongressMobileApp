package com.cs5520.numad20su_congressmobile.models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * https://projects.propublica.org/api-docs/congress-api/bills/#get-a-specific-bill
 * https://projects.propublica.org/api-docs/congress-api/bills/#get-subjects-for-a-specific-bill
 */
public class Bill {

    // TODO Change access modifiers as appropriate
    // TODO Make getter/setter methods so that this works with Firebase Realtime Database

    // ACCESS GIVEN BY 'GET A SPECIFIC BILL'
    public String bill_id; // TODO Change access modifiers appropriately
    public String bill_type;
    public String number;
    public String billUri;
    public String title;
    public String shortTitle;

    public String sponsorId;
    public String congressDotGovUrl;
    public String introducedDate;
    public boolean active;

    // Dates
    public Date lastVote;
    public Date housePassage;
    public Date senatePassage;
    public Date enacted;
    public Date vetoed;

    public Integer cosponsors;
    public JSONArray cosponsorsByParty;
    public Integer withdrawnCosponsors;

    public String primarySubject;
    public String committees;
    public ArrayList<String> committeeCodes;
    public ArrayList<String> subcommitteeCodes;

    public Date latestMajorActionDate;
    public String getLatestMajorAction;
    public Date housePassageVote;
    public Date senatePassageVote;

    public String summary; // TODO Change access modifiers appropriately
    public  String summaryShort;
    public  JSONArray actions;

    // FIELDS WHOSE TYPE IS UNKNOWN
    // array "votes"

    // 'GET SUBJECTS FOR A SPECIFIC BILL'
    ArrayList<Subject> subjects;


//  EXCLUDED FIELDS
//    public String billSlug;
//    public String congress;
//    public String bill;
//    public String sponsorTitle;
//    public String sponsor;
//    public String sponsorUri;
//    public String sponsorParty;
//    public String sponsorState;
//    public String gpoPdfUri;
//    public String govTrackUrl;
//    public JSONArray versions


    // TODO Use GSON to parse a JSON string to create Bill objects
    public Bill(String billId, String billType, String summary) {
        this.bill_id = billId;
        this.bill_type = billType;
        this.summary = summary;
    }
}
