package com.turingoal.qh.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.base.TgBaseFragment;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.fragment.FragmentFactory;

import butterknife.BindView;

/**
 * 主页面
 */

@Route(path = ConstantActivityPath.MAIN)
public class MainActivity extends TgBaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation; // 底部按钮
    private TgBaseFragment mFragment; // 当前Fragment

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialized() {
        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_MAIN); // 默认选中项
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragment).commit();
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:
                        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_MAIN); // 首页
                        break;
                    case R.id.navigation_hall:
                        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_HALL); // 大厅
                        break;
                    case R.id.navigation_task:
                        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_TASK); // 任务
                        break;
                    case R.id.navigation_memo:
                        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_MEMO); // 备忘录
                        break;
                    case R.id.navigation_personal:
                        mFragment = FragmentFactory.createFragment(FragmentFactory.FRAGMENT_PERSONAL); // 个人中心
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment).commit();
                return true;
            }
        });
    }

    /**
     * 点击返回按钮
     */
    @Override
    public void onBackPressed() {
        if (mNavigation.getSelectedItemId() != R.id.navigation_main) { // 先回到主页面，再退出
            mNavigation.setSelectedItemId(R.id.navigation_main);
        } else {
            TgSystemHelper.dbClickExit(this); // 再按一次退出系统
        }
    }
}
