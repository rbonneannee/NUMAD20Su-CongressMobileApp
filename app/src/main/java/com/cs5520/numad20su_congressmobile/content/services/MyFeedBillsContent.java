package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;
import com.android.volley.Response.Listener;
import com.cs5520.numad20su_congressmobile.content.VolleySingleton;
import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.content.services.AbstractViewContent.ProPublicaRequest;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.layoutAdapters.BillsRecyclerViewAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class MyFeedBillsContent implements Listener<String> {

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/bills/";
    private final List<String> billIds;
    private final VolleySingleton volleySingleton;
    private final List<Bill> bills;
    private final BillsRecyclerViewAdapter viewAdapter;

    public MyFeedBillsContent(List<String> billIds,
        Context context,
        FollowInterface followInterface) {
        this.billIds = billIds;
        this.volleySingleton = VolleySingleton.getInstance(context);
        this.bills = new ArrayList<>();
        this.viewAdapter = new BillsRecyclerViewAdapter(this.bills, followInterface);
        requestBills();
    }

    private void requestBills() {
        String str;
        for (String id : billIds) {
            str = id.replace("-116", "").trim();
            this.volleySingleton.getRequestQueue()
                .add(new ProPublicaRequest(ENDPOINT + str + ".json", this, null));
        }
    }

    @Override
    public void onResponse(String jsonText) {
        Gson gson = new Gson();
        Response response = gson.fromJson(jsonText, Response.class);
        bills.add(response.results.get(0));
        this.viewAdapter.notifyItemInserted(this.bills.size() - 1);
    }

    public BillsRecyclerViewAdapter getAdapter() {
        return this.viewAdapter;
    }

    static class Response {

        List<Bill> results;
    }
}


