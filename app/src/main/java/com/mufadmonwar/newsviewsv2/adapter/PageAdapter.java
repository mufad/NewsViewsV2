package com.mufadmonwar.newsviewsv2.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mufadmonwar.newsviewsv2.fragments.ContinueFragment;
import com.mufadmonwar.newsviewsv2.fragments.MyInfoFragment;
import com.mufadmonwar.newsviewsv2.fragments.NewsViewsFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyInfoFragment();
            case 1:
                return new NewsViewsFragment();
            case 2:
                return new ContinueFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}