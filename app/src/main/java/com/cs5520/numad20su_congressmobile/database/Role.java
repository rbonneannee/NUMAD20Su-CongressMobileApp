package com.cs5520.numad20su_congressmobile.database;

import org.json.JSONArray;

import java.util.Date;

/**
 * A Member of Congress holds one or more roles each congressional year.
 */
public class Role {

    private String congress;
    private String chamber;
    private String title;
    private String shortTitle;
    private String state;
    private String party;
    private String leadershipRole;

    private String fecCandidateId;
    private String seniority;
    private String district;
    private boolean atLarge;

    private String ocdId;
    private Date startDate;
    private Date endDate;
    private String office;
    private String phone;
    private String fax;
    private String contactForm;

    private String cookPvi;
    private String dwNominate;
    private String idealPoint;

    private String nextElection;
    private Integer totalVotes;
    private Integer missedVotes;
    private Integer totalPresent;
    private String senateClass;
    private String stateRank;

    private String lisId;
    private Integer billsSponsored;
    private Integer billsCosponsored;
    private float missedVotesPct;
    private float votesAgainstPartyPct;

    private JSONArray committees;
    private JSONArray subcommittees;
}
