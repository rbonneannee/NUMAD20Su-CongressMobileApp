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
import com.cs5520.numad20su_congressmobile.content.models.DummyContent;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MyFeedRecyclerViewAdapter;

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

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      recyclerView.setLayoutManager(new LinearLayoutManager(context));

      // TODO Replace this adapter
      recyclerView.setAdapter(new MyFeedRecyclerViewAdapter(DummyContent.ITEMS));
    }
    return view;
  }

  @Override
  public void registerListener(FollowInterface callback) {
    this.callback = callback;
  }
}