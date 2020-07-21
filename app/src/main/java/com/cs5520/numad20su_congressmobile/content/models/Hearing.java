package com.cs5520.numad20su_congressmobile.content.models;

import java.util.List;

/**
 * https://projects.propublica.org/api-docs/congress-api/committees/#get-hearings-for-a-specific-committee
 */
public class Hearing {
    public String chamber;
    public String committee;
    public String committee_code;
    public String api_uri;
    public String date;
    public String time;
    public String location;
    public String description;
    public List<String> bill_ids;
    public String url;
    public String meeting_type;
}
