package com.cs5520.numad20su_congressmobile.content.services;

import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.CommitteesJsonTextHandler;
import com.cs5520.numad20su_congressmobile.layoutAdapters.CommitteesRecyclerViewAdapter;

import java.util.List;

public class CommitteesViewContent extends AbstractViewContent<Committee> {

    // Lists all committees, house, senate and joint
    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/115/senate/committees.json";

    enum RequestEnum {ALL, TEXT_SEARCH, FILTER}
    private RequestEnum lastRequestType;
    private String query;


    public CommitteesViewContent(Context context) {
        super(context);
        this.viewAdapter = new CommitteesRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    public void getCommittees() {
        this.lastRequestType = RequestEnum.ALL;
        this.submitRequest(ENDPOINT);
    }

    @Override
    List<Committee> getListFromJsonText(String jsonText) {
        return CommitteesJsonTextHandler.extract(jsonText);
    }

    public void loadMore() {
        switch (this.lastRequestType) {
            case FILTER:
                // TODO
                break;
        }
    }
}
