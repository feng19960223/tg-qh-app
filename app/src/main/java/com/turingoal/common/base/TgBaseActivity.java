package com.turingoal.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.turingoal.common.app.TgApplication;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.common.utils.TgExceptionUtil;
import com.turingoal.common.utils.TgKeyboardUtil;
import com.turingoal.qh.R;

import butterknife.ButterKnife;

/**
 * 基类Activity
 */
public abstract class TgBaseActivity extends AppCompatActivity {
    protected static final String TAG = TgBaseActivity.class.getSimpleName();
    private String httpTaskKey = "HttpTaskKey_" + hashCode(); // HttpTaskKey
    private View emptyView; // 数据没有view
    private View errorView; // 数据出错view
    private View loadView; // 数据加载中View
    protected int[] colorRes = new int[]{R.color.md_red_600, R.color.md_pink_600, R.color.md_purple_600, R.color.md_deep_purple_600, R.color.md_indigo_600, R.color.md_blue_600, R.color.md_light_blue_600, R.color.md_cyan_600, R.color.md_teal_600, R.color.md_green_600, R.color.md_light_green_600, R.color.md_lime_600, R.color.md_yellow_600, R.color.md_amber_600, R.color.md_orange_600, R.color.md_deep_orange_600, R.color.md_brown_600, R.color.md_grey_600, R.color.md_blue_grey_600}; // 下拉刷新控件的颜色

    /**
     * 设置布局界面的ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化组件/数据
     */
    protected abstract void initialized();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutID = getLayoutId(); //layoutId
        if (layoutID != 0) {
            setContentView(layoutID);
            getWindow().setBackgroundDrawable(null);
        }
        TgApplication.addActivity(this);
        ButterKnife.bind(this); // ButterKnife
        ARouter.getInstance().inject(this); // ARouter自动解析参数，每个Activity里不用再写
        initialized();
    }

    @Override
    public void onStop() {
        super.onStop();
        TgDialogUtil.closeLoadingProgress(); //关闭进度条
    }

    /**
     * 显示加载进度条
     */
    public void showLoadingProgress() {
        TgDialogUtil.showLoadingProgress(this);
    }

    /**
     * 隐藏加载进度条
     */
    public void dismissLoadingProgress() {
        TgDialogUtil.dismissLoadingProgress();
    }

    /**
     * 默认finish
     */
    public void defaultFinish() {
        TgDialogUtil.closeLoadingProgress(); //关闭进度条
        finish();
    }

    /**
     * HttpTaskKey
     */
    public String getHttpTaskKey() {
        return httpTaskKey;
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        TgApplication.deleteActivity(this); // 从堆栈删除当前Activity
        OkGo.getInstance().cancelTag(httpTaskKey); // 取消http请求
    }

    /**
     * 触摸事件
     */
    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (TgKeyboardUtil.isShouldHideInput(v, ev)) {
                    TgKeyboardUtil.hideSoftInput(this);
                }
                return super.dispatchTouchEvent(ev);
            }
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            TgExceptionUtil.printMessage(e);
        }
        return onTouchEvent(ev);
    }


    /**
     * 没有数据view
     */
    protected View getEmptyView(final ViewGroup viewGroup) {
        if (emptyView == null) {
            emptyView = getLayoutInflater().inflate(R.layout.common_view_empty, viewGroup, false);
        }
        return emptyView;
    }

    /**
     * 每次新创建没有数据view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getNewEmptyView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_empty, viewGroup, false);
    }

    /**
     * 加载数据出错view
     */
    protected View getErrorView(final ViewGroup viewGroup) {
        if (errorView == null) {
            errorView = getLayoutInflater().inflate(R.layout.common_view_error, viewGroup, false);
        }
        return errorView;
    }

    /**
     * 每次新创建加载数据出错view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getNewErrorView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_error, viewGroup, false);
    }

    /**
     * 数据加载中view
     */
    protected View getNewLoadView(final ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.common_view_load, viewGroup, false);
    }

    /**
     * 每次新创建数据加载中view，防止报错The specified child already has a parent. You must call removeView() on the child's parent first.
     */
    protected View getLoadView(final ViewGroup viewGroup) {
        if (loadView == null) {
            loadView = getLayoutInflater().inflate(R.layout.common_view_load, viewGroup, false);
        }
        return loadView;
    }
}
