package com.cs5520.numad20su_congressmobile.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Member;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Member}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MembersRecyclerViewAdapter extends RecyclerView.Adapter<MembersRecyclerViewAdapter.ViewHolder> {

    private final List<Member> mValues;

    public MembersRecyclerViewAdapter(List<Member> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Member member = mValues.get(position);
        holder.mItem = member;
        holder.mIdView.setText(member.id);
        String text = member.short_title + " " + member.first_name + " " + member.last_name;
        holder.mContentView.setText(text);
        holder.followIcon.setImageResource(R.drawable.icons8_heart_50);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Member mItem;
        public ImageView followIcon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            followIcon = view.findViewById(R.id.follow_icon);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}