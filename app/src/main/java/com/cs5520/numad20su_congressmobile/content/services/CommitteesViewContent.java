package com.cs5520.numad20su_congressmobile.content.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.CommitteesJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.BillDetailsActivity;
import com.cs5520.numad20su_congressmobile.controllers.CommitteeDetailsActivity;
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
public class CommitteesViewContent extends AbstractViewContent<Committee> implements CommitteesRecyclerViewAdapter.OnCommitteeListener {

    private GetRequestType prevGetRequestType;
    private String selectedChamber;
    private Context mContext;
    private Activity activity;


    public CommitteesViewContent(Context context, Activity activity) {
        super(context);
        this.viewAdapter = new CommitteesRecyclerViewAdapter(this.resultList, this);
        this.mContext = context;
        this.activity = activity;

        // Lists all committees, house, senate and joint
        this.selectedChamber = "senate";
        this.endpointAllItems = "https://api.propublica.org/congress/v1/" + this.currentSession
                + "/";
;
    }

    // TODO Create filter methods to be called from a filter view
    @Override
    public void getAllItems() {
        this.prevGetRequestType = GetRequestType.ALL;
        this.submitRequest(endpointAllItems + this.selectedChamber + "/committees.json");
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

    @Override
    public void onCommitteeClick(Committee committee) {
        //Toast.makeText(mContext, "Biff!", Toast.LENGTH_LONG).show();
        Intent openDetailsIntent = new Intent(activity, CommitteeDetailsActivity.class);
        openDetailsIntent.putExtra("committee", committee);
        mContext.startActivity(openDetailsIntent);
    }
}
