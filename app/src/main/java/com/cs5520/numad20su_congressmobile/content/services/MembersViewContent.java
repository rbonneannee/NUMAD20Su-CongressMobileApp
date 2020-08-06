package com.cs5520.numad20su_congressmobile.content.services;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.content.services.jsonHandlers.MembersJsonTextHandler;
import com.cs5520.numad20su_congressmobile.controllers.BillDetailsActivity;
import com.cs5520.numad20su_congressmobile.controllers.MemberDetailsActivity;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;

import java.util.List;

public class MembersViewContent extends AbstractViewContent<Member> implements MembersRecyclerViewAdapter.OnMemberListener {

    // TODO Get members from both House and Senate
    private Context mContext;
    private Activity activity;
    private static final String ENDPOINT = "https://api.propublica.org/congress/v1/116/senate/members.json";

    public MembersViewContent(Context context, Activity activity) {
        super(context);
        this.viewAdapter = new MembersRecyclerViewAdapter(this.resultList, this);
        this.mContext = context;
        this.activity = activity;
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


    @Override
    public void onMemberClick(Member member) {
        //Toast.makeText(mContext, "Biff!", Toast.LENGTH_LONG).show();
        Intent openDetailsIntent = new Intent(activity, MemberDetailsActivity.class);
        openDetailsIntent.putExtra("member", member);
        mContext.startActivity(openDetailsIntent);
    }
}
