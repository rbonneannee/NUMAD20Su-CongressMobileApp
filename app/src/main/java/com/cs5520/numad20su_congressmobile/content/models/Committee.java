package com.cs5520.numad20su_congressmobile.content.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * https://projects.propublica.org/api-docs/congress-api/committees/
 */
public class Committee implements Parcelable {
    public String id;
    public String name;
    public String chamber;
    public String url;
    public String api_uri;
    public String chair;
    public String chair_id;
    public String chair_party;
    public String chair_state;
    public String chair_uri;
    public String ranking_member_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi_uri() {
        return api_uri;
    }

    public void setApi_uri(String api_uri) {
        this.api_uri = api_uri;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getChair_id() {
        return chair_id;
    }

    public void setChair_id(String chair_id) {
        this.chair_id = chair_id;
    }

    public String getChair_party() {
        return chair_party;
    }

    public void setChair_party(String chair_party) {
        this.chair_party = chair_party;
    }

    public String getChair_state() {
        return chair_state;
    }

    public void setChair_state(String chair_state) {
        this.chair_state = chair_state;
    }

    public String getChair_uri() {
        return chair_uri;
    }

    public void setChair_uri(String chair_uri) {
        this.chair_uri = chair_uri;
    }

    public String getRanking_member_id() {
        return ranking_member_id;
    }

    public void setRanking_member_id(String ranking_member_id) {
        this.ranking_member_id = ranking_member_id;
    }

    protected Committee(Parcel in) {
        id = in.readString();
        name = in.readString();
        chamber = in.readString();
        url = in.readString();
        api_uri = in.readString();
        chair = in.readString();
        chair_id = in.readString();
        chair_party = in.readString();
        chair_state = in.readString();
        chair_uri = in.readString();
        ranking_member_id = in.readString();
    }

    public static final Creator<Committee> CREATOR = new Creator<Committee>() {
        @Override
        public Committee createFromParcel(Parcel in) {
            return new Committee(in);
        }

        @Override
        public Committee[] newArray(int size) {
            return new Committee[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(chamber);
        parcel.writeString(url);
        parcel.writeString(api_uri);
        parcel.writeString(chair);
        parcel.writeString(chair_id);
        parcel.writeString(chair_party);
        parcel.writeString(chair_state);
        parcel.writeString(chair_uri);
        parcel.writeString(ranking_member_id);
    }
}
