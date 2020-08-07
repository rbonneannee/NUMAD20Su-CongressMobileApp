package com.cs5520.numad20su_congressmobile.content.services;

import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.enums.ChamberType;
import com.cs5520.numad20su_congressmobile.content.enums.GetRequestType;
import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.CommitteesJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.layoutAdapters.CommitteesRecyclerViewAdapter;

import java.util.List;

/**
 * This class extends AbstractViewContent and is a service that provides committee information from
 * the ProPublica Congress database to the application. CommitteeViewContent objects have endpoints
 * to which they can make GET requests, and an enumerated type and a string query for keeping track
 * of the committee information previously requested from the ProPublica server.
 * CommitteeViewContent objects can convert a JSON String response to a list of Committee objects
 * for all and keyword searched committees and can request the next page of results if desired by
 * the user.
 */
public class CommitteesViewContent extends AbstractViewContent<Committee> {

    private GetRequestType prevGetRequestType;
    private ChamberType chamberType;


    public CommitteesViewContent(Context context, FollowInterface followInterface) {
        super(context);
        this.viewAdapter = new CommitteesRecyclerViewAdapter(this.resultList, followInterface);

        // Lists all committees, house, senate and joint
        this.chamberType = ChamberType.JOINT;
        this.endpointAllItems = "https://api.propublica.org/congress/v1/" + this.currentSession
                + "/";
;
    }

    // TODO Create filter methods to be called from a filter view
    @Override
    public void getAllItems() {
        this.prevGetRequestType = GetRequestType.ALL;
        this.submitRequest(endpointAllItems + this.chamberType.toString()
                + "/committees.json");
    }

  @Override
  public List<Committee> getListFromJsonText(String jsonText) {
    return CommitteesJsonTextHandler.extract(jsonText);
  }

    public void loadMore() {
        switch (this.prevGetRequestType) {
            case FILTER:
                // TODO
                break;
        }
    }
}
