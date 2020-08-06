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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getApi_uri() {
        return api_uri;
    }

    public void setApi_uri(String api_uri) {
        this.api_uri = api_uri;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getLeadership_role() {
        return leadership_role;
    }

    public void setLeadership_role(String leadership_role) {
        this.leadership_role = leadership_role;
    }

    public String getTwitter_account() {
        return twitter_account;
    }

    public void setTwitter_account(String twitter_account) {
        this.twitter_account = twitter_account;
    }

    public String getFacebook_account() {
        return facebook_account;
    }

    public void setFacebook_account(String facebook_account) {
        this.facebook_account = facebook_account;
    }

    public String getYoutube_account() {
        return youtube_account;
    }

    public void setYoutube_account(String youtube_account) {
        this.youtube_account = youtube_account;
    }

    public String getGovtrack_id() {
        return govtrack_id;
    }

    public void setGovtrack_id(String govtrack_id) {
        this.govtrack_id = govtrack_id;
    }

    public String getCspan_id() {
        return cspan_id;
    }

    public void setCspan_id(String cspan_id) {
        this.cspan_id = cspan_id;
    }

    public String getVotesmart_id() {
        return votesmart_id;
    }

    public void setVotesmart_id(String votesmart_id) {
        this.votesmart_id = votesmart_id;
    }

    public String getIcpsr_id() {
        return icpsr_id;
    }

    public void setIcpsr_id(String icpsr_id) {
        this.icpsr_id = icpsr_id;
    }

    public String getCrp_id() {
        return crp_id;
    }

    public void setCrp_id(String crp_id) {
        this.crp_id = crp_id;
    }

    public String getGoogle_entity_id() {
        return google_entity_id;
    }

    public void setGoogle_entity_id(String google_entity_id) {
        this.google_entity_id = google_entity_id;
    }

    public String getFec_candidate_id() {
        return fec_candidate_id;
    }

    public void setFec_candidate_id(String fec_candidate_id) {
        this.fec_candidate_id = fec_candidate_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRss_url() {
        return rss_url;
    }

    public void setRss_url(String rss_url) {
        this.rss_url = rss_url;
    }

    public String getContact_form() {
        return contact_form;
    }

    public void setContact_form(String contact_form) {
        this.contact_form = contact_form;
    }

    public boolean isIn_office() {
        return in_office;
    }

    public void setIn_office(boolean in_office) {
        this.in_office = in_office;
    }

    public String getCook_pvi() {
        return cook_pvi;
    }

    public void setCook_pvi(String cook_pvi) {
        this.cook_pvi = cook_pvi;
    }

    public float getDw_nominate() {
        return dw_nominate;
    }

    public void setDw_nominate(float dw_nominate) {
        this.dw_nominate = dw_nominate;
    }

    public String getIdeal_point() {
        return ideal_point;
    }

    public void setIdeal_point(String ideal_point) {
        this.ideal_point = ideal_point;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getNext_election() {
        return next_election;
    }

    public void setNext_election(String next_election) {
        this.next_election = next_election;
    }

    public int getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(int total_votes) {
        this.total_votes = total_votes;
    }

    public int getMissed_votes() {
        return missed_votes;
    }

    public void setMissed_votes(int missed_votes) {
        this.missed_votes = missed_votes;
    }

    public int getTotal_present() {
        return total_present;
    }

    public void setTotal_present(int total_present) {
        this.total_present = total_present;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getOcd_id() {
        return ocd_id;
    }

    public void setOcd_id(String ocd_id) {
        this.ocd_id = ocd_id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSenate_class() {
        return senate_class;
    }

    public void setSenate_class(String senate_class) {
        this.senate_class = senate_class;
    }

    public String getState_rank() {
        return state_rank;
    }

    public void setState_rank(String state_rank) {
        this.state_rank = state_rank;
    }

    public String getLis_id() {
        return lis_id;
    }

    public void setLis_id(String lis_id) {
        this.lis_id = lis_id;
    }

    public float getMissed_votes_pct() {
        return missed_votes_pct;
    }

    public void setMissed_votes_pct(float missed_votes_pct) {
        this.missed_votes_pct = missed_votes_pct;
    }

    public float getVotes_with_party_pct() {
        return votes_with_party_pct;
    }

    public void setVotes_with_party_pct(float votes_with_party_pct) {
        this.votes_with_party_pct = votes_with_party_pct;
    }

    public float getVotes_against_party_pct() {
        return votes_against_party_pct;
    }

    public void setVotes_against_party_pct(float votes_against_party_pct) {
        this.votes_against_party_pct = votes_against_party_pct;
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
