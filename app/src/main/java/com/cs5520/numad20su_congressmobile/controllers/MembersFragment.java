package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.enums.ChamberType;
import com.cs5520.numad20su_congressmobile.content.services.MembersViewContent;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A fragment representing a list of Items.
 */
public class MembersFragment extends Fragment implements FollowTrigger {

  private FollowInterface followInterface;
  private MembersViewContent membersViewContent;
  private TextInputEditText searchFld;
  private RadioGroup radioGroupChamber;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_members, container, false);

    this.membersViewContent = new MembersViewContent(this.getContext(), followInterface);
    this.membersViewContent.getAllItems();

    // Set the adapter
    RecyclerView recyclerView = view.findViewById(R.id.list);
    Context context = recyclerView.getContext();
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.setAdapter(membersViewContent.getViewAdapter());

    // Search listener
    this.searchFld = view.findViewById(R.id.textInputEditText);
    this.searchFld.setHint("Search members by district number");
    view.findViewById(R.id.imageButtonSearch).setOnClickListener(view1 -> {
      String s = searchFld.getText().toString();
    });

    // Radio button listener
    this.radioGroupChamber = view.findViewById(R.id.radioGroup);
    this.radioGroupChamber.setOnCheckedChangeListener((radioGroup, checkedId) -> {

      switch (checkedId) {
        case R.id.radioButton_house:
          membersViewContent.setChamberType(ChamberType.HOUSE);
          break;
        case R.id.radioButton_senate:
          membersViewContent.setChamberType(ChamberType.SENATE);
          break;
      }
      membersViewContent.getAllItems();
    });

    return view;
  }

  @Override
  public void registerListener(FollowInterface callback) {
    this.followInterface = callback;
  }
}