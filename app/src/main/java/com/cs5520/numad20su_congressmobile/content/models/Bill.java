package com.cs5520.numad20su_congressmobile.content.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * https://projects.propublica.org/api-docs/congress-api/bills/
 */
public class Bill implements Parcelable {

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

  public Bill() {
    // Default constructor for Firebase
  }

  // Copy constructor
  public Bill(Bill bill) {
    this.bill_id = bill.bill_id;
    this.bill_type = bill.bill_type;
    this.number = bill.number;
    this.bill_uri = bill.bill_uri;
    this.title = bill.title;
    this.sponsor_title = bill.sponsor_title;
    this.sponsor_id = bill.sponsor_id;
    this.sponsor_name = bill.sponsor_name;
    this.sponsor_state = bill.sponsor_state;
    this.sponsor_party = bill.sponsor_party;
    this.sponsor_uri = bill.sponsor_uri;
    this.gpo_pdf_uri = bill.gpo_pdf_uri;
    this.congressdotgov_url = bill.congressdotgov_url;
    this.govtrack_url = bill.govtrack_url;
    this.introduced_date = bill.introduced_date;
    this.active = bill.active;
    this.house_passage = bill.house_passage;
    this.senate_passage = bill.senate_passage;
    this.enacted = bill.enacted;
    this.vetoed = bill.vetoed;
    this.cosponsors = bill.cosponsors;
    this.committees = bill.committees;
    this.committee_codes = bill.committee_codes;
    this.subcommittee_codes = bill.subcommittee_codes;
    this.primary_subject = bill.primary_subject;
    this.summary = bill.summary;
    this.summary_short = bill.summary_short;
    this.latest_major_action_date = bill.latest_major_action_date;
    this.latest_major_action = bill.latest_major_action;
  }

  protected Bill(Parcel in) {
    bill_id = in.readString();
    bill_type = in.readString();
    number = in.readString();
    bill_uri = in.readString();
    title = in.readString();
    sponsor_title = in.readString();
    sponsor_id = in.readString();
    sponsor_name = in.readString();
    sponsor_state = in.readString();
    sponsor_party = in.readString();
    sponsor_uri = in.readString();
    gpo_pdf_uri = in.readString();
    congressdotgov_url = in.readString();
    govtrack_url = in.readString();
    introduced_date = in.readString();
    active = in.readByte() != 0;
    house_passage = in.readString();
    senate_passage = in.readString();
    enacted = in.readString();
    vetoed = in.readString();
    cosponsors = in.readInt();
    committees = in.readString();
    committee_codes = in.createStringArrayList();
    subcommittee_codes = in.createStringArrayList();
    primary_subject = in.readString();
    summary = in.readString();
    summary_short = in.readString();
    latest_major_action_date = in.readString();
    latest_major_action = in.readString();
  }

  public static final Creator<Bill> CREATOR = new Creator<Bill>() {
    @Override
    public Bill createFromParcel(Parcel in) {
      return new Bill(in);
    }

    @Override
    public Bill[] newArray(int size) {
      return new Bill[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(bill_id);
    parcel.writeString(bill_type);
    parcel.writeString(number);
    parcel.writeString(bill_uri);
    parcel.writeString(title);
    parcel.writeString(sponsor_title);
    parcel.writeString(sponsor_id);
    parcel.writeString(sponsor_name);
    parcel.writeString(sponsor_state);
    parcel.writeString(sponsor_party);
    parcel.writeString(sponsor_uri);
    parcel.writeString(gpo_pdf_uri);
    parcel.writeString(congressdotgov_url);
    parcel.writeString(govtrack_url);
    parcel.writeString(introduced_date);
    parcel.writeByte((byte) (active ? 1 : 0));
    parcel.writeString(house_passage);
    parcel.writeString(senate_passage);
    parcel.writeString(enacted);
    parcel.writeString(vetoed);
    parcel.writeInt(cosponsors);
    parcel.writeString(committees);
    parcel.writeStringList(committee_codes);
    parcel.writeStringList(subcommittee_codes);
    parcel.writeString(primary_subject);
    parcel.writeString(summary);
    parcel.writeString(summary_short);
    parcel.writeString(latest_major_action_date);
    parcel.writeString(latest_major_action);
  }
}
