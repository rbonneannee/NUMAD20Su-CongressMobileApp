package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioGroup;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.enums.ChamberType;
import com.cs5520.numad20su_congressmobile.content.services.MembersViewContent;
import com.cs5520.numad20su_congressmobile.layoutAdapters.MembersRecyclerViewAdapter;

/**
 * A fragment representing a list of Items.
 */
public class MembersFragment extends Fragment implements FollowTrigger {

    private FollowInterface followInterface;
    private MembersViewContent membersViewContent;

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
        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search by member name");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.onActionViewExpanded();
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                MembersRecyclerViewAdapter adapter =
                    (MembersRecyclerViewAdapter) membersViewContent.getViewAdapter();
                adapter.getFilter().filter(query);
                return false;
            }
        });

        // Radio button listener
        RadioGroup radioGroupChamber = view.findViewById(R.id.radioGroup);
        radioGroupChamber.setOnCheckedChangeListener((radioGroup, checkedId) -> {

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