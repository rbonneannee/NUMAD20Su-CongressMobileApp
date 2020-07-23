package com.cs5520.numad20su_congressmobile.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Bill;

import java.util.List;

public class BillsRecyclerViewAdapter extends RecyclerView.Adapter<BillsRecyclerViewAdapter.ViewHolder> {

    private List<Bill> items;

    public BillsRecyclerViewAdapter(List<Bill> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bill = items.get(position);
        holder.idView.setText(items.get(position).bill_id);
        holder.contentView.setText(items.get(position).title);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView idView;
        public final TextView contentView;
        public Bill bill;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            idView = view.findViewById(R.id.item_number);
            contentView = view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }

}
