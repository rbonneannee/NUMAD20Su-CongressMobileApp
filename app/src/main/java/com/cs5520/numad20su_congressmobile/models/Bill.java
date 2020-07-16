package com.cs5520.numad20su_congressmobile.models;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * https://projects.propublica.org/api-docs/congress-api/bills/#get-a-specific-bill
 * https://projects.propublica.org/api-docs/congress-api/bills/#get-subjects-for-a-specific-bill
 */
public class Bill {

    // ACCESS GIVEN BY 'GET A SPECIFIC BILL'
    public String billId; // TODO Change access modifiers appropriately
    private String billType;
    private String number;
    private String billUri;
    private String title;
    private String shortTitle;

    private String sponsorId;
    private String congressDotGovUrl;
    private String introducedDate;
    private boolean active;

    // Dates
    private Date lastVote;
    private Date housePassage;
    private Date senatePassage;
    private Date enacted;
    private Date vetoed;

    private Integer cosponsors;
    private JSONArray cosponsorsByParty;
    private Integer withdrawnCosponsors;

    private String primarySubject;
    private String committees;
    private ArrayList<String> committeeCodes;
    private ArrayList<String> subcommitteeCodes;

    private Date latestMajorActionDate;
    private String getLatestMajorAction;
    private Date housePassageVote;
    private Date senatePassageVote;

    public String summary; // TODO Change access modifiers appropriately
    private String summaryShort;
    private JSONArray actions;

    // FIELDS WHOSE TYPE IS UNKNOWN
    // array "votes"

    // 'GET SUBJECTS FOR A SPECIFIC BILL'
    ArrayList<Subject> subjects;


//  EXCLUDED FIELDS
//    private String billSlug;
//    private String congress;
//    private String bill;
//    private String sponsorTitle;
//    private String sponsor;
//    private String sponsorUri;
//    private String sponsorParty;
//    private String sponsorState;
//    private String gpoPdfUri;
//    private String govTrackUrl;
//    private JSONArray versions


    // TODO Use GSON to parse a JSON string to create Bill objects
    public Bill(String billId, String billType, String summary) {
        this.billId = billId;
        this.billType = billType;
        this.summary = summary;
    }
}
