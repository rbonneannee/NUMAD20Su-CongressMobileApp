package com.cs5520.numad20su_congressmobile.models;

import java.util.ArrayList;

/**
 * A committee holds one or more hearings on an array of bills.
 * https://projects.propublica.org/api-docs/congress-api/committees/#get-hearings-for-a-specific-committee
 */
public class Hearing {

    private String congress;
    private Integer numResults;

    private String chamber;
    private String committee;
    private String committeeCode;
    private String apiUri;

    private String date;
    private String time;
    private String location;
    private String description;
    private ArrayList<String> billIds;
    private String url;
    private String meetingType;

}
