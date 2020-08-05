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

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBill_uri() {
        return bill_uri;
    }

    public void setBill_uri(String bill_uri) {
        this.bill_uri = bill_uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSponsor_title() {
        return sponsor_title;
    }

    public void setSponsor_title(String sponsor_title) {
        this.sponsor_title = sponsor_title;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getSponsor_state() {
        return sponsor_state;
    }

    public void setSponsor_state(String sponsor_state) {
        this.sponsor_state = sponsor_state;
    }

    public String getSponsor_party() {
        return sponsor_party;
    }

    public void setSponsor_party(String sponsor_party) {
        this.sponsor_party = sponsor_party;
    }

    public String getSponsor_uri() {
        return sponsor_uri;
    }

    public void setSponsor_uri(String sponsor_uri) {
        this.sponsor_uri = sponsor_uri;
    }

    public String getGpo_pdf_uri() {
        return gpo_pdf_uri;
    }

    public void setGpo_pdf_uri(String gpo_pdf_uri) {
        this.gpo_pdf_uri = gpo_pdf_uri;
    }

    public String getCongressdotgov_url() {
        return congressdotgov_url;
    }

    public void setCongressdotgov_url(String congressdotgov_url) {
        this.congressdotgov_url = congressdotgov_url;
    }

    public String getGovtrack_url() {
        return govtrack_url;
    }

    public void setGovtrack_url(String govtrack_url) {
        this.govtrack_url = govtrack_url;
    }

    public String getIntroduced_date() {
        return introduced_date;
    }

    public void setIntroduced_date(String introduced_date) {
        this.introduced_date = introduced_date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getHouse_passage() {
        return house_passage;
    }

    public void setHouse_passage(String house_passage) {
        this.house_passage = house_passage;
    }

    public String getSenate_passage() {
        return senate_passage;
    }

    public void setSenate_passage(String senate_passage) {
        this.senate_passage = senate_passage;
    }

    public String getEnacted() {
        return enacted;
    }

    public void setEnacted(String enacted) {
        this.enacted = enacted;
    }

    public String getVetoed() {
        return vetoed;
    }

    public void setVetoed(String vetoed) {
        this.vetoed = vetoed;
    }

    public int getCosponsors() {
        return cosponsors;
    }

    public void setCosponsors(int cosponsors) {
        this.cosponsors = cosponsors;
    }

    public String getCommittees() {
        return committees;
    }

    public void setCommittees(String committees) {
        this.committees = committees;
    }

    public List<String> getCommittee_codes() {
        return committee_codes;
    }

    public void setCommittee_codes(List<String> committee_codes) {
        this.committee_codes = committee_codes;
    }

    public List<String> getSubcommittee_codes() {
        return subcommittee_codes;
    }

    public void setSubcommittee_codes(List<String> subcommittee_codes) {
        this.subcommittee_codes = subcommittee_codes;
    }

    public String getPrimary_subject() {
        return primary_subject;
    }

    public void setPrimary_subject(String primary_subject) {
        this.primary_subject = primary_subject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary_short() {
        return summary_short;
    }

    public void setSummary_short(String summary_short) {
        this.summary_short = summary_short;
    }

    public String getLatest_major_action_date() {
        return latest_major_action_date;
    }

    public void setLatest_major_action_date(String latest_major_action_date) {
        this.latest_major_action_date = latest_major_action_date;
    }

    public String getLatest_major_action() {
        return latest_major_action;
    }

    public void setLatest_major_action(String latest_major_action) {
        this.latest_major_action = latest_major_action;
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
