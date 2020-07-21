package com.cs5520.numad20su_congressmobile.content;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.controllers.BillsRecyclerViewAdapter;

import java.util.List;

public class BillsViewContent extends AbstractViewContent<Bill> {

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/both/bills/active.json";

    public BillsViewContent(Context context) {
        super(context);
        this.viewAdapter = new BillsRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    public void getBills() {
        this.submitRequest(ENDPOINT);
    }

    @Override
    List<Bill> getListFromJsonText(String jsonText) {
        return BillsJsonTextHandler.extract(jsonText);
    }
}
