package com.cs5520.numad20su_congressmobile.content;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.controllers.BillsRecyclerViewAdapter;

import java.util.List;

public class BillsViewContent extends AbstractViewContent<Bill> {

    private String REQUEST_RECENT = "recent";
    private String REQUEST_SEARCH = "search";
    private String REQUEST_FILTER = "filter";

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/both/bills/active.json";
    private static final String ENDPOINT_SEARCH = "https://api.propublica.org/congress/v1/bills/subjects/";

    private static String requestType = "";


    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    public void getBills() {
        requestType = REQUEST_RECENT;
        this.submitRequest(ENDPOINT);
    }

    public void searchBills(String query) {
        requestType = REQUEST_SEARCH;
        this.submitRequest(ENDPOINT_SEARCH + query + ".json");
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        switch (requestType) {
            case "recent":
                return BillsJsonTextHandler.extract(jsonText);
            case "search":
                return BillsSearchJsonTextHandler.extract(jsonText);
            case "filter":
                // TODO
            default:
                return null;
        }
    }
}
