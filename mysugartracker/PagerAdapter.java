package com.example.mysugartracker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    //Return the Fragments associated with a specifies position @param position
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new Graph();
            case 1: return new Table();
            case 2: return new Analysis();
            default: return null;
        }

    }
    //return the number of views available
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
