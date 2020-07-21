package com.cs5520.numad20su_congressmobile.content.models;

import java.util.List;

/**
 * https://projects.propublica.org/api-docs/congress-api/members/#get-a-specific-member
 */
public class Role {
    public String congress;
    public String chamber;
    public String title;
    public String short_title;
    public String state;
    public String party;
    public String leadership_role;
    public String fec_candidate_id;
    public String seniority;
    public String district;
    public boolean at_large;
    public String ocd_id;
    public String start_date;
    public String end_date;
    public String office;
    public String phone;
    public String fax;
    public String contact_form;
    public String cook_pvi;
    public float dw_nominate;
    public String ideal_point;
    public String next_election;
    public int total_votes;
    public int missed_votes;
    public int total_present;
    public String senate_class;
    public String state_rank;
    public String lis_id;
    public int bills_sponsored;
    public int bills_cosponsored;
    public float missed_votes_pct;
    public float votes_with_party_pct;
    public float votes_against_party_pct;
    List<Committee> committees;
}
