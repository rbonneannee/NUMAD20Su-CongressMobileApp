package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
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

    private TextInputEditText mKeywordSearchFld;
    private boolean isLoading = false;
    private BillsViewContent billsViewContent = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills, container, false);

        billsViewContent = new BillsViewContent(this.getContext());
        billsViewContent.getRecentBills();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(billsViewContent.getViewAdapter());
            initScrollListener(recyclerView);
        }

        return view;
    }

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
                        billsViewContent.loadMore();
                    }
                }
            }
        });
    }
}