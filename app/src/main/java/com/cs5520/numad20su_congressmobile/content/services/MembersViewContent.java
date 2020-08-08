package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.enums.ChamberType;
import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.MembersJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;
import java.util.List;

public class MembersViewContent extends AbstractViewContent<Member> {

  private ChamberType chamberType;

  public MembersViewContent(Context context, FollowInterface followInterface) {
    super(context);
    this.viewAdapter = new MembersRecyclerViewAdapter(context, this.resultList, followInterface);

    this.chamberType = ChamberType.HOUSE;
    this.endpointAllItems = "https://api.propublica.org/congress/v1/" + this.currentSession + "/";
  }

  @Override
  public void getAllItems() {
    this.resultList.clear();
    this.submitRequest(this.endpointAllItems + this.chamberType.toString()
            + "/members.json");
  }

  @Override
  public List<Member> getListFromJsonText(String jsonText) {
    List<Member> list = MembersJsonTextHandler.extract(jsonText);
    MembersRecyclerViewAdapter adapter = (MembersRecyclerViewAdapter) this.viewAdapter;
    adapter.setPreFilteredList(list);
    return list;
  }

  public void setChamberType(ChamberType chamberType) {
    this.chamberType = chamberType;
  }
}
