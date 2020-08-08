package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.services.MembersViewContent;

/**
 * A fragment representing a list of Items.
 */
public class MembersFragment extends Fragment implements FollowTrigger {

  private FollowInterface callback;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_members, container, false);

    MembersViewContent membersViewContent = new MembersViewContent(this.getContext(),
        callback);
    membersViewContent.getAllItems();

    // Set the adapter
    RecyclerView recyclerView = view.findViewById(R.id.list_members);
    Context context = recyclerView.getContext();
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.setAdapter(membersViewContent.getViewAdapter());
    return view;
  }

  @Override
  public void registerListener(FollowInterface callback) {
    this.callback = callback;
  }
}