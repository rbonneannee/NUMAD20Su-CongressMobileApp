package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;

import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.MembersJsonTextHandler;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;

import java.util.List;

public class MembersViewContent extends AbstractViewContent<Member> {

    // TODO Get members from both House and Senate
    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/senate/members.json";

    public MembersViewContent(Context context) {
        super(context);
        this.viewAdapter = new MembersRecyclerViewAdapter(this.resultList);
    }

    // TODO Create filter methods to be called from a filter view
    @Override
    public void getAllItems() {
        this.submitRequest(ENDPOINT);
    }

    @Override
    public List<Member> getListFromJsonText(String jsonText) {
        return MembersJsonTextHandler.extract(jsonText);
    }


}
