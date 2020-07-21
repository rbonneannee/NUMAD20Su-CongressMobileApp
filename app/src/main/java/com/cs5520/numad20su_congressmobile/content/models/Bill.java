package com.cs5520.numad20su_congressmobile.content.models;

import java.util.List;

/**
 * https://projects.propublica.org/api-docs/congress-api/bills/
 */
public class Bill {
    public String bill_id;
    public String bill_type;
    public String number;
    public String bill_uri;
    public String title;
    public String sponsor_title;
    public String sponsor_id;
    public String sponsor_name;
    public String sponsor_state;
    public String sponsor_party;
    public String sponsor_uri;
    public String gpo_pdf_uri;
    public String congressdotgov_url;
    public String govtrack_url;
    public String introduced_date;
    public boolean active;
    public String house_passage;
    public String senate_passage;
    public String enacted;
    public String vetoed;
    public int cosponsors;
    public String committees;
    public List<String> committee_codes;
    public List<String> subcommittee_codes;
    public String primary_subject;
    public String summary;
    public String summary_short;
    public String latest_major_action_date;
    public String latest_major_action;
}
