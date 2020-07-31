package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.BillsViewContent;
import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class BillsFragment extends Fragment {

    private TextInputEditText mKeywordSearchFld;
    private boolean isLoading = false;
    private BillsViewContent billsViewContent = null;
    private Button searchBtn;
    private TextInputEditText searchFld;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_bills, container, false);

        billsViewContent = new BillsViewContent(this.getContext());
        billsViewContent.getBills();

        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(billsViewContent.getViewAdapter());
        initScrollListener(recyclerView);

        this.searchFld = view.findViewById(R.id.textInputEditText_keyword);
        view.findViewById(R.id.imageButton_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = searchFld.getText().toString();
                billsViewContent.searchBillsByKeyword(keyword);
            }
        });

        return view;
    }

    private void initScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == billsViewContent.getResultList().size() - 1) {
                        Log.d("hello", "hi");
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        final List<Bill> itemsList = billsViewContent.getResultList();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                billsViewContent.loadMore();
                billsViewContent.getViewAdapter().notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }
}