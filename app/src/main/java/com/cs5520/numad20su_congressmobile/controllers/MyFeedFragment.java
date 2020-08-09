package com.cs5520.numad20su_congressmobile.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.services.MyFeedBillsContent;
import com.cs5520.numad20su_congressmobile.content.services.MyFeedCommitteesContent;
import com.cs5520.numad20su_congressmobile.content.services.MyFeedMembersContent;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface.TYPE;
import com.cs5520.numad20su_congressmobile.layoutAdapters.BillsRecyclerViewAdapter;
import com.cs5520.numad20su_congressmobile.layoutAdapters.CommitteesRecyclerViewAdapter;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;
import java.util.ArrayList;

public class MyFeedFragment extends Fragment implements FollowTrigger {

    private FollowInterface followInterface;

    public MyFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);
        RecyclerView billsView = view.findViewById(R.id.my_feed_bills_list);
        RecyclerView committeesView = view.findViewById(R.id.my_feed_committees_list);
        RecyclerView membersView = view.findViewById(R.id.my_feed_members_list);

        // Set the layout managers
        billsView.setLayoutManager(new LinearLayoutManager(billsView.getContext()));
        committeesView.setLayoutManager(new LinearLayoutManager(committeesView.getContext()));
        membersView.setLayoutManager(new LinearLayoutManager(membersView.getContext()));

        // Create the content providers
        MyFeedBillsContent myFeedBillsContent =
            new MyFeedBillsContent(
                followInterface.following(TYPE.Bill), this.getContext(),
                new BillsRecyclerViewAdapter(new ArrayList<>(), followInterface));
        MyFeedCommitteesContent myFeedCommitteesContent =
            new MyFeedCommitteesContent(
                followInterface.following(TYPE.Committee), this.getContext(),
                new CommitteesRecyclerViewAdapter(new ArrayList<>(), followInterface));
        MyFeedMembersContent myFeedMembersContent =
            new MyFeedMembersContent(
                followInterface.following(TYPE.Member), this.getContext(),
                new MembersRecyclerViewAdapter(this.getContext(), new ArrayList<>(),
                    followInterface));

        // Set the adapters
        billsView.setAdapter(myFeedBillsContent.getAdapter());
        committeesView.setAdapter(myFeedCommitteesContent.getAdapter());
        membersView.setAdapter(myFeedMembersContent.getAdapter());

        return view;
    }

    @Override
    public void registerListener(FollowInterface callback) {
        this.followInterface = callback;
    }
}