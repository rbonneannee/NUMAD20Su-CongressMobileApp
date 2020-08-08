package com.cs5520.numad20su_congressmobile.content.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * https://projects.propublica.org/api-docs/congress-api/members/
 */
public class Member implements Parcelable {

  public String id;
  public String title;
  public String short_title;
  public String api_uri;
  public String first_name;
  public String middle_name;
  public String last_name;
  public String suffix;
  public String date_of_birth;
  public String gender;
  public String party;
  public String leadership_role;
  public String twitter_account;
  public String facebook_account;
  public String youtube_account;
  public String govtrack_id;
  public String cspan_id;
  public String votesmart_id;
  public String icpsr_id;
  public String crp_id;
  public String google_entity_id;
  public String fec_candidate_id;
  public String url;
  public String rss_url;
  public String contact_form;
  public boolean in_office;
  public String cook_pvi;
  public float dw_nominate;
  public String ideal_point;
  public String seniority;
  public String next_election;
  public int total_votes;
  public int missed_votes;
  public int total_present;
  public String last_updated;
  public String ocd_id;
  public String office;
  public String phone;
  public String fax;
  public String state;
  public String senate_class;
  public String state_rank;
  public String lis_id;
  public float missed_votes_pct;
  public float votes_with_party_pct;
  public float votes_against_party_pct;

  public Member() {
    // Default constructor
  }

  protected Member(Parcel in) {
    id = in.readString();
    title = in.readString();
    short_title = in.readString();
    api_uri = in.readString();
    first_name = in.readString();
    middle_name = in.readString();
    last_name = in.readString();
    suffix = in.readString();
    date_of_birth = in.readString();
    gender = in.readString();
    party = in.readString();
    leadership_role = in.readString();
    twitter_account = in.readString();
    facebook_account = in.readString();
    youtube_account = in.readString();
    govtrack_id = in.readString();
    cspan_id = in.readString();
    votesmart_id = in.readString();
    icpsr_id = in.readString();
    crp_id = in.readString();
    google_entity_id = in.readString();
    fec_candidate_id = in.readString();
    url = in.readString();
    rss_url = in.readString();
    contact_form = in.readString();
    in_office = in.readByte() != 0;
    cook_pvi = in.readString();
    dw_nominate = in.readFloat();
    ideal_point = in.readString();
    seniority = in.readString();
    next_election = in.readString();
    total_votes = in.readInt();
    missed_votes = in.readInt();
    total_present = in.readInt();
    last_updated = in.readString();
    ocd_id = in.readString();
    office = in.readString();
    phone = in.readString();
    fax = in.readString();
    state = in.readString();
    senate_class = in.readString();
    state_rank = in.readString();
    lis_id = in.readString();
    missed_votes_pct = in.readFloat();
    votes_with_party_pct = in.readFloat();
    votes_against_party_pct = in.readFloat();
  }

  public Member(Member member) {
    this.id = member.id;
    this.title = member.title;
    this.short_title = member.short_title;
    this.api_uri = member.api_uri;
    this.first_name = member.first_name;
    this.middle_name = member.middle_name;
    this.last_name = member.last_name;
    this.suffix = member.suffix;
    this.date_of_birth = member.date_of_birth;
    this.gender = member.gender;
    this.party = member.party;
    this.leadership_role = member.leadership_role;
    this.twitter_account = member.twitter_account;
    this.facebook_account = member.facebook_account;
    this.youtube_account = member.youtube_account;
    this.govtrack_id = member.govtrack_id;
    this.cspan_id = member.cspan_id;
    this.votesmart_id = member.votesmart_id;
    this.icpsr_id = member.icpsr_id;
    this.crp_id = member.crp_id;
    this.google_entity_id = member.google_entity_id;
    this.fec_candidate_id = member.fec_candidate_id;
    this.url = member.url;
    this.rss_url = member.rss_url;
    this.contact_form = member.contact_form;
    this.in_office = member.in_office;
    this.cook_pvi = member.cook_pvi;
    this.dw_nominate = member.dw_nominate;
    this.ideal_point = member.ideal_point;
    this.seniority = member.seniority;
    this.next_election = member.next_election;
    this.total_votes = member.total_votes;
    this.missed_votes = member.missed_votes;
    this.total_present = member.total_present;
    this.last_updated = member.last_updated;
    this.ocd_id = member.ocd_id;
    this.office = member.office;
    this.phone = member.phone;
    this.fax = member.fax;
    this.state = member.state;
    this.senate_class = member.senate_class;
    this.state_rank = member.state_rank;
    this.lis_id = member.lis_id;
    this.missed_votes_pct = member.missed_votes_pct;
    this.votes_with_party_pct = member.votes_with_party_pct;
    this.votes_against_party_pct = member.votes_against_party_pct;
  }

  public static final Creator<Member> CREATOR = new Creator<Member>() {
    @Override
    public Member createFromParcel(Parcel in) {
      return new Member(in);
    }

    @Override
    public Member[] newArray(int size) {
      return new Member[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(title);
    parcel.writeString(short_title);
    parcel.writeString(api_uri);
    parcel.writeString(first_name);
    parcel.writeString(middle_name);
    parcel.writeString(last_name);
    parcel.writeString(suffix);
    parcel.writeString(date_of_birth);
    parcel.writeString(gender);
    parcel.writeString(party);
    parcel.writeString(leadership_role);
    parcel.writeString(twitter_account);
    parcel.writeString(facebook_account);
    parcel.writeString(youtube_account);
    parcel.writeString(govtrack_id);
    parcel.writeString(cspan_id);
    parcel.writeString(votesmart_id);
    parcel.writeString(icpsr_id);
    parcel.writeString(crp_id);
    parcel.writeString(google_entity_id);
    parcel.writeString(fec_candidate_id);
    parcel.writeString(url);
    parcel.writeString(rss_url);
    parcel.writeString(contact_form);
    parcel.writeByte((byte) (in_office ? 1 : 0));
    parcel.writeString(cook_pvi);
    parcel.writeFloat(dw_nominate);
    parcel.writeString(ideal_point);
    parcel.writeString(seniority);
    parcel.writeString(next_election);
    parcel.writeInt(total_votes);
    parcel.writeInt(missed_votes);
    parcel.writeInt(total_present);
    parcel.writeString(last_updated);
    parcel.writeString(ocd_id);
    parcel.writeString(office);
    parcel.writeString(phone);
    parcel.writeString(fax);
    parcel.writeString(state);
    parcel.writeString(senate_class);
    parcel.writeString(state_rank);
    parcel.writeString(lis_id);
    parcel.writeFloat(missed_votes_pct);
    parcel.writeFloat(votes_with_party_pct);
    parcel.writeFloat(votes_against_party_pct);
  }
}
