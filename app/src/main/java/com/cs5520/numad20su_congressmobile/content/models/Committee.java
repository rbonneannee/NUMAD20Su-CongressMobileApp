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

  public Committee(Committee committee) {
    this.id = committee.id;
    this.name = committee.name;
    this.chamber = committee.chamber;
    this.url = committee.url;
    this.api_uri = committee.api_uri;
    this.chair = committee.chair;
    this.chair_id = committee.chair_id;
    this.chair_party = committee.chair_party;
    this.chair_state = committee.chair_state;
    this.chair_uri = committee.chair_uri;
    this.ranking_member_id = committee.ranking_member_id;
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
