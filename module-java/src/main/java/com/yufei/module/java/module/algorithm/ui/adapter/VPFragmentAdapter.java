package com.yufei.module.java.module.algorithm.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.yufei.baselibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class VPFragmentAdapter extends FragmentPagerAdapter {

    private  List<BaseFragment> mFragments = new ArrayList<>();
    private  List<String> mFragmentTitles = new ArrayList<>();

    public VPFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titls) {
        super(fm);
        mFragments = fragments;
        mFragmentTitles = titls;
    }

    private void addFragment(BaseFragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
