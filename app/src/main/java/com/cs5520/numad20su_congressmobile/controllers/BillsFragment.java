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
import com.cs5520.numad20su_congressmobile.content.BillsViewContent;
import com.google.android.material.textfield.TextInputEditText;

public class BillsFragment extends Fragment {

    private boolean isLoading = false;
    private BillsViewContent billsViewContent = null;
    private TextInputEditText searchFld;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills, container, false);

        billsViewContent = new BillsViewContent(this.getContext());
        billsViewContent.getRecentBills();

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(billsViewContent.getViewAdapter());
        initScrollListener(recyclerView);

        this.searchFld = view.findViewById(R.id.textInputEditText_keyword);
        view.findViewById(R.id.imageButton_search)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String query = searchFld.getText().toString();
                        billsViewContent.searchBills(query);

                    }
                });
        return view;
    }

    // TODO Make sure this doesn't break for fast scrolling (test on physical device)
    // TODO Use loading animation for 'Executing' Volley phase from submitRequest()
    // TODO Use DownloadManager and loading animation if payload too big for Volley
    private void initScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null
                            && (linearLayoutManager.findLastCompletelyVisibleItemPosition()
                            == billsViewContent.getResultList().size() - 1)) {

                        // Launch data load in a new thread
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                billsViewContent.loadMore();
                                isLoading = false;
                            }
                        }, 5);
                    }
                }
            }
        });
    }
}