package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cs5520.numad20su_congressmobile.R;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;
    private final String[] fragmentNames;

    public MainFragmentPagerAdapter(Context context,
                                    FragmentManager fragmentManager,
                                    int behavior) {
        super(fragmentManager, behavior);

        fragments = new Fragment[]{
                new MyFeedFragment(),
                new BillsFragment(),
                new CommitteesFragment(),
                new MembersFragment()
        };

        fragmentNames = new String[]{
                context.getString(R.string.heading_my_feed),
                context.getString(R.string.heading_bills),
                context.getString(R.string.heading_committees),
                context.getString(R.string.heading_members)
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentNames[position];
    }
}
