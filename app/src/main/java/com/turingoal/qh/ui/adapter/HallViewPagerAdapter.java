package com.turingoal.qh.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 大厅adapter
 */

public class HallViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> titles; // title
    private List<Fragment> fragments; // 创建一个List<Fragment>

    public HallViewPagerAdapter(FragmentManager fragmentManager, List<String> titles, List<Fragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
