package com.cs5520.numad20su_congressmobile.content;


import android.content.Context;
import android.util.Log;

import com.cs5520.numad20su_congressmobile.controllers.BillsRecyclerViewAdapter;
import com.cs5520.numad20su_congressmobile.models.Bill;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Make API requests to ProPublica Congress API Bills endpoint
 * <p>
 * Provides methods to request JSON objects from https://api.propublica.org/congress/v1/bills/. The
 * resulting Bills are then made available as Java Lists.
 */
public class BillsViewContent extends AbstractViewContent<Bill> {

    private static final String TAG = "BillsViewContent";
    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/house/bills/introduced.json";

    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
    }

    // TODO Compile an actual list of bills to display
    // TODO Create filter methods to be called from a filter view
    public void getBills() {
        this.submitRequest(ENDPOINT, "");
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        Log.d(TAG, "formatResponse");
        JsonTextHandler billJsonClass = new JsonTextHandler(jsonText);
        return billJsonClass.getResults();
    }


}
