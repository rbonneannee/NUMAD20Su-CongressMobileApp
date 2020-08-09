package com.cs5520.numad20su_congressmobile.content.services;


import android.content.Context;
import com.android.volley.Response.Listener;
import com.cs5520.numad20su_congressmobile.content.VolleySingleton;
import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.content.models.Role;
import com.cs5520.numad20su_congressmobile.content.services.AbstractViewContent.ProPublicaRequest;
import com.cs5520.numad20su_congressmobile.content.services.MyFeedMembersContent.Response.Result;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;
import com.google.gson.Gson;
import java.util.List;


public class MyFeedMembersContent implements Listener<String> {

    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/members/";

    private final VolleySingleton volleySingleton;
    private final MembersRecyclerViewAdapter viewAdapter;

    public MyFeedMembersContent(List<String> memberIds,
        Context context,
        MembersRecyclerViewAdapter viewAdapter) {
        this.volleySingleton = VolleySingleton.getInstance(context);
        this.viewAdapter = viewAdapter;
        requestMembers(memberIds);
    }

    private void requestMembers(List<String> memberIds) {
        for (String id : memberIds) {
            this.volleySingleton.getRequestQueue()
                .add(new ProPublicaRequest(ENDPOINT + id + ".json", this, null));
        }
    }

    @Override
    public void onResponse(String jsonText) {
        Gson gson = new Gson();
        Response response = gson.fromJson(jsonText, Response.class);
        Result result = response.results.get(0);
        Role role = result.roles.get(0);
        Member member = new Member();
        member.id = result.id;
        member.first_name = result.first_name;
        member.last_name = result.last_name;
        member.short_title = role.short_title;
        member.state = role.state;
        member.party = role.party;
        this.viewAdapter.add(member);
    }

    public MembersRecyclerViewAdapter getAdapter() {
        return this.viewAdapter;
    }

    static class Response {

        List<Result> results;

        static class Result {

            String id;
            String first_name;
            String last_name;
            List<Role> roles;
        }
    }
}


