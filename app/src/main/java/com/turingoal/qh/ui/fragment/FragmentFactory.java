package com.turingoal.qh.ui.fragment;

import com.turingoal.common.base.TgBaseFragment;

import java.util.HashMap;

/**
 * Fragment工厂
 */

public class FragmentFactory {
    public static final int FRAGMENT_MAIN = 0; // 首页
    public static final int FRAGMENT_HALL = 1; // 大厅
    public static final int FRAGMENT_TASK = 2; // 任务
    public static final int FRAGMENT_MEMO = 3; // 备忘录
    public static final int FRAGMENT_PERSONAL = 4; // 个人中心
    private static HashMap<Integer, TgBaseFragment> mFragments = null; //Fragment缓存

    private FragmentFactory() {
        throw new Error("Fragment工厂类不能实例化！");
    }

    /**
     * 根据类型创建Fragment
     */
    public static TgBaseFragment createFragment(final int type) {
        if (mFragments == null) {
            mFragments = new HashMap<>();
        }
        TgBaseFragment fragment = mFragments.get(type);
        if (fragment == null) {
            switch (type) {
                case FRAGMENT_MAIN:
                    fragment = new MainFragment(); // 首页
                    break;
                case FRAGMENT_HALL:
                    fragment = new HallFragment(); // 大厅
                    break;
                case FRAGMENT_TASK:
                    fragment = new TaskFragment(); // 任务
                    break;
                case FRAGMENT_MEMO:
                    fragment = new MemoFragment(); // 备忘录
                    break;
                case FRAGMENT_PERSONAL:
                    fragment = new PersonalFragment();  // 个人中心
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                mFragments.put(type, fragment); // 如果新new了Fragment，加到缓存中
            }
        }
        return fragment;
    }

    /**
     * 清理数据缓存，退出的时候要清理重新加载
     */
    public static void fragmetnFactoryClearData() {
        mFragments = null;
    }
}
