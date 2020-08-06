package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.services.CommitteesViewContent;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A fragment representing a list of Items.
 */
public class CommitteesFragment extends Fragment {

    private boolean isLoading = false;
    private CommitteesViewContent committeesViewContent;
    private TextInputEditText searchFld;

    public CommitteesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_committees, container, false);

        this.committeesViewContent = new CommitteesViewContent(this.getContext(), getActivity());
        this.committeesViewContent.getAllItems();

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list_committees);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(committeesViewContent.getViewAdapter());
        initScrollListener(recyclerView);

        this.searchFld = view.findViewById(R.id.textInputEditText_committeeName);
        view.findViewById(R.id.imageButton_searchCommittee)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String committeeName = searchFld.getText().toString();
                // TODO implement and call billsViewContent.searchCommittee(committeeName);
            }
        });

        return  view;
    }

    private void initScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null
                        && (linearLayoutManager.findLastCompletelyVisibleItemPosition()
                            == committeesViewContent.getResultList().size() - 1)) {

                        // Launch data load in a new thread
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                committeesViewContent.loadMore();
                                isLoading = false;
                            }
                        }, 5);
                    }
                }
            }
        });
    }
}