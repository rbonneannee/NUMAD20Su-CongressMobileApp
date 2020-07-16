package com.cs5520.numad20su_congressmobile.models;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * https://projects.propublica.org/api-docs/congress-api/committees/#get-a-specific-committee
 */
public class Committee {

    private String congress;
    private String chamber;
    private String id;
    private String nameCommittee;
    private String url;

    private String chairId;
    private String rankingMemberId;
    private JSONArray currentMembers;
    private JSONArray subcommittees;

    private ArrayList<Hearing> hearings;


    // EXCLUDED FIELDS
//    private Integer numResults;
//    private String chair;
//    private String chairParty;
//    private String chairState;
//    private JSONArray formerMembers;
}
