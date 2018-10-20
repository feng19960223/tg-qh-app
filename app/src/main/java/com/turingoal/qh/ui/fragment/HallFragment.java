package com.turingoal.qh.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.turingoal.common.base.TgBaseFragment;
import com.turingoal.qh.R;
import com.turingoal.qh.ui.adapter.HallViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 大厅
 */

public class HallFragment extends TgBaseFragment {
    @BindView(R.id.tlHall)
    TabLayout tlHall; // tab
    @BindView(R.id.vpHall)
    ViewPager vpHall; //　内容

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hall;
    }

    @Override
    protected void initialized() {
        List<Fragment> fragments = new ArrayList<>(); // Fragment数据
        List<String> titles = new ArrayList<>(); // title数据
        titles.add(getString(R.string.tab_all));
        fragments.add(new HallAllFragment()); // 全部任务
        titles.add(getString(R.string.tab_conventional));
        fragments.add(new HallConventionalFragment()); // 常规任务
        titles.add(getString(R.string.tab_reward));
        fragments.add(new HallRewardFragment()); // 悬赏任务
        titles.add(getString(R.string.tab_assigned));
        fragments.add(new HallAssignedFragment()); // 指派任务
        HallViewPagerAdapter hallViewPagerAdapter = new HallViewPagerAdapter(getFragmentManager(), titles, fragments);
        vpHall.setAdapter(hallViewPagerAdapter);
        tlHall.setupWithViewPager(vpHall);
    }
}
