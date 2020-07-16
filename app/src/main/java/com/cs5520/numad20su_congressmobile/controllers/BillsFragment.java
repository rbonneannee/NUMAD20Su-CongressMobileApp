package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.DummyBillContent;
import com.cs5520.numad20su_congressmobile.content.DummyContent;
import com.cs5520.numad20su_congressmobile.network.DownloadCallback;

/**
 * A fragment representing a list of Items.
 */
public class BillsFragment extends Fragment implements DownloadCallback {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BillsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BillsFragment newInstance(int columnCount) {
        BillsFragment fragment = new BillsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new BillsRecyclerViewAdapter(DummyBillContent.BILLS));
        }
        return view;
    }

    @Override
    public void updateFromDownload(String result) {
        // TODO
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        // TODO
        return null;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        // TODO
    }

    @Override
    public void finishDownloading() {
        // TODO
    }
}