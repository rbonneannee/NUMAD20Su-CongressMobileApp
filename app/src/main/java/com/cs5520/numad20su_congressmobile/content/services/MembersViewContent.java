package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.enums.ChamberType;
import com.cs5520.numad20su_congressmobile.content.enums.GetRequestType;
import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.MembersJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;
import java.util.List;

public class MembersViewContent extends AbstractViewContent<Member> {

  private GetRequestType prevGetRequestType;
  private ChamberType chamberType;

  public MembersViewContent(Context context, FollowInterface followInterface) {
    super(context);
    this.viewAdapter = new MembersRecyclerViewAdapter(this.resultList, followInterface);

    this.chamberType = ChamberType.HOUSE;
    this.endpointAllItems = "https://api.propublica.org/congress/v1/" + this.currentSession + "/";
  }

  // TODO Create filter methods to be called from a filter view
  @Override
  public void getAllItems() {
    this.resultList.clear();
    this.submitRequest(this.endpointAllItems + this.chamberType.toString()
            + "/members.json");

    // TODO may be able to delete this
    this.prevGetRequestType = GetRequestType.ALL;
  }

  @Override
  public List<Member> getListFromJsonText(String jsonText) {
    return MembersJsonTextHandler.extract(jsonText);
  }

  public void setChamberType(ChamberType chamberType) {
    this.chamberType = chamberType;
  }

  public void searchMember(String s) {
  }
}
