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
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface.TYPE;

// TODO Put logic to populate 'My Feed' here

public class MyFeedFragment extends Fragment implements FollowTrigger {

  private FollowInterface callback;

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
        new MyFeedBillsContent(callback.following(TYPE.Bill), this.getContext(), callback);

    // Set the adapters
    billsView.setAdapter(myFeedBillsContent.getAdapter());

    return view;
  }

  @Override
  public void registerListener(FollowInterface callback) {
    this.callback = callback;
  }
}