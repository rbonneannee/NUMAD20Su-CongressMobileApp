package com.cs5520.numad20su_congressmobile.content;

import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.cs5520.numad20su_congressmobile.controllers.CommitteesRecyclerViewAdapter;

import java.util.List;

public class CommitteesViewContent extends AbstractViewContent<Committee> {

    // TODO List all committees, house, senate and joint
    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/115/senate/committees.json";

    public CommitteesViewContent(Context context) {
        super(context);
        this.viewAdapter = new CommitteesRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    public void getBills() {
        this.submitRequest(ENDPOINT);
    }

    @Override
    List<Committee> getListFromJsonText(String jsonText) {
        return CommitteesJsonTextHandler.extract(jsonText);
    }
}
