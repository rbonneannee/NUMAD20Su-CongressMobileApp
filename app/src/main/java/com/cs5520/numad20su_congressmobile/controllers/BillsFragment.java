package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.services.BillsViewContent;
import com.google.android.material.textfield.TextInputEditText;

public class BillsFragment extends Fragment implements FollowTrigger {

    private boolean isLoading = false;
    private BillsViewContent billsViewContent = null;
    private TextInputEditText searchFld;
    private FollowInterface followInterface;

    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills, container, false);

        billsViewContent = new BillsViewContent(this.getContext(), followInterface);
        billsViewContent.getAllItems();

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(billsViewContent.getViewAdapter());
        initScrollListener(recyclerView);

        SearchView searchView=view.findViewById(R.id.searchView);
        searchView.setQueryHint("Keyword search");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.clearFocus();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                billsViewContent.getAllItems();
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                billsViewContent.getBillsWithKeyword(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    billsViewContent.getAllItems();
                }
                return false;
            }
        });



        /*
        this.searchFld = view.findViewById(R.id.textInputEditText);
        this.searchFld.setHint("Keyword search");
        view.findViewById(R.id.imageButtonSearch)
            .setOnClickListener(view1 -> {
                String query = searchFld.getText().toString();
                billsViewContent.getBillsWithKeyword(query);
            });

         */
        return view;
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
                        == billsViewContent.getResultList().size() - 1)) {

                        // Launch data load in a new thread
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            billsViewContent.loadMore();
                            isLoading = false;
                        }, 5);
                    }
                }
            }
        });
    }

    @Override
    public void registerListener(FollowInterface callback) {
        this.followInterface = callback;
    }
}